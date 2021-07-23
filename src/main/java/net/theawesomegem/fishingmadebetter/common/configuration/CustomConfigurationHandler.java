package net.theawesomegem.fishingmadebetter.common.configuration;

import com.google.common.io.Files;
import com.google.gson.*;
import net.minecraft.item.Item;
import net.theawesomegem.fishingmadebetter.DefaultFishes;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.proxy.CommonProxy;
import net.theawesomegem.fishingmadebetter.util.TextUtils;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomConfigurationHandler {

    public static Map<String, FishData> fishDataMap = new HashMap<>();
    public static Map<String, ArrayList<Integer>> possibleBaitMap = new HashMap<>();

    private static File fishDataFolder = null;

    public static void preInit(File file) {
        File modFolder = new File(file, ModInfo.MOD_ID);
        if(!modFolder.exists() || !modFolder.isDirectory()) {
            if(!modFolder.mkdir()) CommonProxy.Logger.log(Level.FATAL, "Could not create the folder for configuration.");
        }
        if(!modFolder.exists()) return;

        fishDataFolder = new File(modFolder, "fishdata");
        if(!fishDataFolder.exists() || !fishDataFolder.isDirectory()) {
            if(!fishDataFolder.mkdir()) CommonProxy.Logger.log(Level.FATAL, "Could not create the folder for fish data.");
        }
    }

    public static void postInit(){
        loadFishes();
    }

    public static boolean loadFishes() {
        if(fishDataFolder == null || !fishDataFolder.exists()) {
            CommonProxy.Logger.log(Level.FATAL, "Could not load fishes because the folder for the fish data does not exist.");
            return false;
        }

        File[] configFiles = fishDataFolder.listFiles();

        if(configFiles.length < 1) {
            initDefaultFishes(fishDataFolder);
            configFiles = fishDataFolder.listFiles();
            CommonProxy.Logger.log(Level.INFO, "Initializing default fishes since no file(s) were found in the fishdata folder.");
        }

        int fileCount = 0;

        fishDataMap.clear();

        for(File configFile : configFiles) {
            if(!configFile.exists() || !configFile.getName().endsWith(".json")) continue;

            CommonProxy.Logger.log(Level.INFO, "Loading: " + configFile.getName());
            
            loadConfig(configFile);

            fileCount++;
        }

        CommonProxy.Logger.log(Level.INFO, String.format("Loaded %d file(s).", fileCount));

        initBaitMap();
        
        return true;
    }
    
    private static void initBaitMap() {
    	possibleBaitMap.clear();
    	for(Map.Entry<String, FishData> dataEntry : fishDataMap.entrySet()) {
    		if(dataEntry.getValue().baitItemMap.isEmpty()) continue;
    		for(Map.Entry<String, Integer[]> baitEntry : dataEntry.getValue().baitItemMap.entrySet()) {
    			ArrayList<Integer> metaArray = new ArrayList<Integer>();
    			if(possibleBaitMap.containsKey(baitEntry.getKey())) metaArray = possibleBaitMap.get(baitEntry.getKey());
    			metaArray.addAll(Arrays.asList(baitEntry.getValue()));
    			possibleBaitMap.put(baitEntry.getKey(), metaArray);
    		}
    	}
    }
    
    private static void initDefaultFishes(File fishDataFolder) {
        Map<String, Map<String, FishData>> defaultFishDataMap = DefaultFishes.getDefaultFishMap();

        for(Map.Entry<String, Map<String, FishData>> defaultFishEntry : defaultFishDataMap.entrySet()) {
            JsonObject fishDataJson = new JsonObject();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            for(FishData fishData : defaultFishEntry.getValue().values()) {
                JsonElement element = gson.toJsonTree(fishData);
                fishDataJson.add(fishData.fishId, element);
            }

            String fishDataString = gson.toJson(fishDataJson);
            File file = new File(fishDataFolder, String.format("%s.json", defaultFishEntry.getKey()));
            
            boolean fileCreated;
            try {
            	fileCreated = file.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if(!fileCreated) {
                CommonProxy.Logger.log(Level.FATAL, "Could not create the fish data file.");
                continue;
            }

            try {
                boolean writeable = file.setWritable(true);

                if(!writeable) {
                    CommonProxy.Logger.log(Level.FATAL, "Cannot write/save the fish data file inside the fishdata folder. Insufficient permission.");
                    continue;
                }

                PrintWriter writer = new PrintWriter(file);

                writer.write(fishDataString);

                writer.flush();
                writer.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadConfig(File configFile) {
        if(configFile == null || !configFile.exists()) {
            CommonProxy.Logger.log(Level.FATAL, "Could not load the fish data config file. File does not exist.");
            return;
        }

        try {
            boolean readable = configFile.setReadable(true);

            if(!readable) {
                CommonProxy.Logger.log(Level.FATAL, String.format("Cannot read file %s. No permission to read the file.", configFile.getName()));
                return;
            }

            String fishDataString = Files.toString(configFile, Charset.defaultCharset());
            loadFishData(new JsonParser().parse(fishDataString));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadFishData(JsonElement jsonElement) {
        JsonObject fishDataJson = jsonElement.getAsJsonObject();

        Gson gson = new Gson();

        Map<String, FishData> tmpFishMap = new HashMap<>();
        
        for(Map.Entry<String, JsonElement> fishDataEntry : fishDataJson.entrySet()) {
            JsonElement fishDataElement = fishDataEntry.getValue();
            FishData fishData = gson.fromJson(fishDataElement, FishData.class);

            if(TextUtils.isEmpty(fishData.itemId)) continue;

            Item item = Item.getByNameOrId(fishData.itemId);

            if(item == null) {
            	CommonProxy.Logger.log(Level.WARN, "Tried loading fish data entry with invalid item: " + fishData.itemId + " , skipping entry.");
            	continue;
            }

            tmpFishMap.put(fishDataEntry.getKey(), fishData);
        }

        CommonProxy.Logger.log(Level.INFO, "=========== Loaded Fishes ============");

        for(Map.Entry<String, FishData> entry : tmpFishMap.entrySet()) {
        	fishDataMap.put(entry.getKey(), entry.getValue());
        	CommonProxy.Logger.log(Level.INFO, entry.getValue().fishId);
        }

        CommonProxy.Logger.log(Level.INFO, "======================================");
    }

    public static Collection<FishData> getAllFishData() {
        return fishDataMap.values();
    }
}
