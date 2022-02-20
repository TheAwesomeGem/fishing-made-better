package net.theawesomegem.fishingmadebetter.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import net.minecraftforge.fml.common.eventhandler.ListenerList;
import net.theawesomegem.fishingmadebetter.proxy.CommonProxy;

public class HandlerUtil {
	
	//From Charles445 TweakerFramework
	
	private static HandlerUtil instance;

	private final Class c_EventBus;
	private final Field f_listeners;
	private final Field f_busID;
	
	public HandlerUtil() throws Exception
	{
		c_EventBus = Class.forName("net.minecraftforge.fml.common.eventhandler.EventBus");
		f_listeners = c_EventBus.getDeclaredField("listeners");
		f_listeners.setAccessible(true);
		f_busID = c_EventBus.getDeclaredField("busID");
		f_busID.setAccessible(true);
	}
	
	/** 
	 * @param name : Class name to remove from the event bus.
	 * @param specific : If specified, removes only a single method.
	 * @return : If specific, returns the specific IEventListener, otherwise returns the entire handler.
	 * @throws Exception
	 */
	@Nullable
	public static Object findAndRemoveHandlerFromEventBus(String name, @Nullable String specific) throws Exception
	{
		boolean criticalCrash = false;
		
		try
		{
			if(instance==null)
				instance = new HandlerUtil();
			
			boolean found_listener = false;
			ConcurrentHashMap<Object, ArrayList<IEventListener>> listeners = (ConcurrentHashMap<Object, ArrayList<IEventListener>>) instance.f_listeners.get(MinecraftForge.EVENT_BUS);
			//TweakerFramework.logger.debug("Listener Size: "+listeners.size());
			
			Object handler = null;
			
			for(Map.Entry<Object, ArrayList<IEventListener>> listener_entry : listeners.entrySet())
			{
				handler = listener_entry.getKey();
				
				String handlerName = handler.getClass().getName();
				
				//Name fixup for static handlers
				if(handlerName.equals("java.lang.Class"))
				{
					handlerName = ((Class)handler).getName();
				}
				
				if (handlerName.equals(name))
				{
					found_listener=true;
					
					if(specific==null)
					{
						MinecraftForge.EVENT_BUS.unregister(handler);
						CommonProxy.Logger.log(Level.INFO, "Found and removed "+name+" from the event bus");
						//TweakerFramework.logger.debug("Listener Size Post Unregister: "+listeners.size());
						return handler;
					}
					else
					{
						//Specific
						ArrayList<IEventListener> eventListeners = listener_entry.getValue();
						//TweakerFramework.logger.debug("EventListener Size Pre Unregister: "+eventListeners.size());
						
						Iterator<IEventListener> elIterator = eventListeners.iterator();
						
						while(elIterator.hasNext())
						{
							IEventListener eventListener = elIterator.next();
							if(eventListener.toString().contains(specific)) //TODO consider accuracy, or not, as this allows for desc usage as-is
							{
								//From this point on, any crash in here is a critical failure
								criticalCrash = true;
								
								//Remove it from the collection
								elIterator.remove();
								
								//TweakerFramework.logger.debug("EventListener Size Post Unregister: "+eventListeners.size());
								
								//Tell the internal bus to get rid of it as well
								int busID = instance.f_busID.getInt(MinecraftForge.EVENT_BUS);
								ListenerList.unregisterAll(busID, eventListener);
								
								
								CommonProxy.Logger.log(Level.INFO, "Found and removed "+name+" IEventListener "+specific+" from the event bus");
								return eventListener;
							}
						}
						
						//break;
						//Normally this would break and be satisfied that it found the matching handler but no listener to wrap
						//However, due to certain mods registering multiple handlers of the same class, continuing to the next handler is appropriate instead
						//TweakerFramework.logger.debug("Instance Missing Specific EventListener, Continuing...");
					}
				}
			}
			//TODO inform?
			return null;
		}
		catch(Exception e)
		{
			if(criticalCrash)
			{
				//throw new CriticalException(e);
				throw new RuntimeException(e);
			}
			else
			{
				throw e;
			}
		}
	}
}
