package com.PIPPIP5789.mfrex.pyrofantasy.init;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PyroFantasyIOInit {

    /*public static final String
    MECHANICALCORE = "{\n" +
            "  \"type\": \"CustomToolOreDictAnvilRecipes\",\n" +
            "  \"skill\": \"artisanry\",\n" +
            "  \"research\": \"Mechanical Core\",\n" +
            "  \"tool_type\": \"hammer\",\n" +
            "  \"is_tool_recipe\": false,\n" +
            "  \"anvil_tier\": -1,\n" +
            "  \"recipe_time\": 2,\n" +
            "  \"recipe_hammer\": -1,\n" +
            "  \"output_hot\": false,\n" +
            "  \"pattern\": [\n" +
            "    \"I I\",\n" +
            "    \" C \",\n" +
            "    \"I I\"\n" +
            "  ],\n" +
            "  \"key\": {\n" +
            "    \"I\": {\n" +
            "      \"type\": \"oreDict\",\n" +
            "      \"ore\": \"ingotIron\"\n" +
            "    },\n" +
            "    \"C\": {\n" +
            "      \"nbt\": \"{mf_custom_materials:{main_material:\\\"composite_alloy\\\"}}\",\n" +
            "      \"item\": \"minefantasyreforged:plate\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"result\": {\n" +
            "    \"item\": \"embers:mech_core\"\n" +
            "  }\n" +
            "}";*/

    public static void initTypes(FMLPreInitializationEvent event) {
        try {
            String tempPath = "MineFantasyReforged/custom/registry/pyrofantasy/";
            new File(event.getModConfigurationDirectory(), tempPath).mkdirs();
            File tempFile = new File(event.getModConfigurationDirectory(), tempPath + "metal_types" + ".json");
            PrintWriter tempWriter = new PrintWriter(tempFile);
/*
            tempWriter.println("{\n" +
                    "    \"mod\": \"pyrofantasy\",\n" +
                    "    \"metals\": [\n" +
                    "        {\n" +
                    "            \"name\": \"lead\",\n" +
                    "            \"oreDictList\": \"ingotLead\",\n" +
                    "            \"properties\": {\n" +
                    "                \"tier\": 1,\n" +
                    "                \"durability\": 1.6,\n" +
                    "                \"flexibility\": 0.9,\n" +
                    "                \"sharpness\": 1.3,\n" +
                    "                \"hardness\": 2.5,\n" +
                    "                \"resistance\": 20,\n" +
                    "                \"density\": 3.5,\n" +
                    "                \"melting_point\": 1400,\n" +
                    "                \"rarity\": 0,\n" +
                    "                \"craft_tier\": 1,\n" +
                    "                \"craft_time_modifier\": 5.0,\n" +
                    "                \"unbreakable\": false\n" +
                    "            },\n" +
                    "            \"armour_stats\": {\n" +
                    "                \"cutting\": 1.0,\n" +
                    "                \"blunt\": 1.0,\n" +
                    "                \"piercing\": 1.0\n" +
                    "            },\n" +
                    "            \"color\": {\n" +
                    "                \"red\": 140,\n" +
                    "                \"green\": 127,\n" +
                    "                \"blue\": 157\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"dawnstone\",\n" +
                    "            \"oreDictList\": \"ingotDawnstone\",\n" +
                    "            \"properties\": {\n" +
                    "                \"tier\": 5,\n" +
                    "                \"durability\": 6.0,\n" +
                    "                \"flexibility\": 1.5,\n" +
                    "                \"sharpness\": 3.8,\n" +
                    "                \"hardness\": 3.3,\n" +
                    "                \"resistance\": 60,\n" +
                    "                \"density\": 3.2,\n" +
                    "                \"melting_point\": 2500,\n" +
                    "                \"rarity\": 0,\n" +
                    "                \"craft_tier\": 4,\n" +
                    "                \"craft_time_modifier\": 10.0,\n" +
                    "                \"unbreakable\": false\n" +
                    "            },\n" +
                    "            \"armour_stats\": {\n" +
                    "                \"cutting\": 1.0,\n" +
                    "                \"blunt\": 1.0,\n" +
                    "                \"piercing\": 1.0\n" +
                    "            },\n" +
                    "            \"color\": {\n" +
                    "                \"red\": 255,\n" +
                    "                \"green\": 182,\n" +
                    "                \"blue\": 72\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"aluminum\",\n" +
                    "            \"oreDictList\": \"ingotAluminum\",\n" +
                    "            \"properties\": {\n" +
                    "                \"tier\": 0,\n" +
                    "                \"durability\": 1.2,\n" +
                    "                \"flexibility\": 0.7,\n" +
                    "                \"sharpness\": 0.0,\n" +
                    "                \"hardness\": 1.2,\n" +
                    "                \"resistance\": 10,\n" +
                    "                \"density\": 2.0,\n" +
                    "                \"melting_point\": 800,\n" +
                    "                \"rarity\": -1,\n" +
                    "                \"craft_tier\": -1,\n" +
                    "                \"craft_time_modifier\": 2.0,\n" +
                    "                \"unbreakable\": false\n" +
                    "            },\n" +
                    "            \"armour_stats\": {\n" +
                    "                \"cutting\": 1.0,\n" +
                    "                \"blunt\": 1.0,\n" +
                    "                \"piercing\": 1.0\n" +
                    "            },\n" +
                    "            \"color\": {\n" +
                    "                \"red\": 234,\n" +
                    "                \"green\": 152,\n" +
                    "                \"blue\": 134\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"electrum\",\n" +
                    "            \"oreDictList\": \"ingotElectrum\",\n" +
                    "            \"properties\": {\n" +
                    "                \"tier\": 4,\n" +
                    "                \"durability\": 7.0,\n" +
                    "                \"flexibility\": 1.5,\n" +
                    "                \"sharpness\": 2.0,\n" +
                    "                \"hardness\": 3.0,\n" +
                    "                \"resistance\": 55,\n" +
                    "                \"density\": 3.0,\n" +
                    "                \"melting_point\": 2000,\n" +
                    "                \"rarity\": 0,\n" +
                    "                \"craft_tier\": 3,\n" +
                    "                \"craft_time_modifier\": 8.0,\n" +
                    "                \"unbreakable\": false\n" +
                    "            },\n" +
                    "            \"armour_stats\": {\n" +
                    "                \"cutting\": 1.0,\n" +
                    "                \"blunt\": 1.0,\n" +
                    "                \"piercing\": 1.0\n" +
                    "            },\n" +
                    "            \"color\": {\n" +
                    "                \"red\": 240,\n" +
                    "                \"green\": 216,\n" +
                    "                \"blue\": 113\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"nickel\",\n" +
                    "            \"oreDictList\": \"ingotNickel\",\n" +
                    "            \"properties\": {\n" +
                    "                \"tier\": 0,\n" +
                    "                \"durability\": 1.2,\n" +
                    "                \"flexibility\": 0.8,\n" +
                    "                \"sharpness\": 0.5,\n" +
                    "                \"hardness\": 1.0,\n" +
                    "                \"resistance\": 50,\n" +
                    "                \"density\": 3.0,\n" +
                    "                \"melting_point\": 1200,\n" +
                    "                \"rarity\": 0,\n" +
                    "                \"craft_tier\": 0,\n" +
                    "                \"craft_time_modifier\": 2.0,\n" +
                    "                \"unbreakable\": false\n" +
                    "            },\n" +
                    "            \"armour_stats\": {\n" +
                    "                \"cutting\": 1.0,\n" +
                    "                \"blunt\": 1.0,\n" +
                    "                \"piercing\": 1.0\n" +
                    "            },\n" +
                    "            \"color\": {\n" +
                    "                \"red\": 171,\n" +
                    "                \"green\": 181,\n" +
                    "                \"blue\": 152\n" +
                    "            }\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}");
            tempWriter.flush();
            */

            tempWriter.close();
        }
        catch(IOException e) {
        }
    }

    public static void initOres(FMLPreInitializationEvent event) {
        try {
            String path = "pyrotech/";
            File file = new File(event.getModConfigurationDirectory(), path + "core.compat.Ore-Custom.json");
            file.deleteOnExit();
            PrintWriter writer = new PrintWriter(file);
            writer.println("{\n" +
                    "  \"__comments\": [\n" +
                    "    \"WARNING: All changes should be made to the file with the name Custom\",\n" +
                    "    \"in the title. Changes made to the Generated file will be overwritten.\",\n" +
                    "    \"\",\n" +
                    "    \"This file defines ore compatibility.\",\n" +
                    "    \"\",\n" +
                    "    \"Slag content will be generated for each oredict entry.\",\n" +
                    "    \"\",\n" +
                    "    \"The first valid key in each langKey list will be chosen; reorder keys\",\n" +
                    "    \"to change which key is selected.\",\n" +
                    "    \"\",\n" +
                    "    \"The first valid nugget in each output list will be chosen; reorder nuggets\",\n" +
                    "    \"to change which nugget is selected.\",\n" +
                    "    \"\",\n" +
                    "    \"Slag color is a hex color code without the #\",\n" +
                    "    \"\",\n" +
                    "    \"Lang keys are in the format: (domain):(key)\",\n" +
                    "    \"\",\n" +
                    "    \"Output item strings are in the format: (domain):(path):(meta)\"\n" +
                    "  ],\n" +
                    "  \"oredict\": {\n" +
                    "    \"oreGold\": {\n" +
                    "      \"slagColor\": \"fcee4b\",\n" +
                    "      \"langKey\": [\n" +
                    "        \"minecraft:tile.oreGold\"\n" +
                    "      ],\n" +
                    "      \"output\": [\n" +
                    "        \"minecraft:gold_ingot\"\n" +
                    "      ],\n" +
                    "      \"bloomYieldMinMax\": [\n" +
                    "        1,\n" +
                    "        3\n" +
                    "      ],\n" +
                    "      \"slagBloomYieldMinMax\": [\n" +
                    "        0,\n" +
                    "        2\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"oreIron\": {\n" +
                    "      \"slagColor\": \"d8af93\",\n" +
                    "      \"langKey\": [\n" +
                    "        \"minecraft:tile.oreIron\"\n" +
                    "      ],\n" +
                    "      \"output\": [\n" +
                    "        \"minecraft:iron_ingot\"\n" +
                    "      ],\n" +
                    "      \"bloomYieldMinMax\": [\n" +
                    "        1,\n" +
                    "        3\n" +
                    "      ],\n" +
                    "      \"slagBloomYieldMinMax\": [\n" +
                    "        0,\n" +
                    "        2\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"oreCopper\": {\n" +
                    "      \"slagColor\": \"23f78f\",\n" +
                    "      \"langKey\": [\n" +
                    "        \"minefantasyreforged:tile.copper_ore\"\n" +
                    "      ],\n" +
                    "      \"output\": [\n" +
                    "        \"minefantasyreforged:copper_ingot\"\n" +
                    "      ],\n" +
                    "      \"bloomYieldMinMax\": [\n" +
                    "        1,\n" +
                    "        3\n" +
                    "      ],\n" +
                    "      \"slagBloomYieldMinMax\": [\n" +
                    "        0,\n" +
                    "        2\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"oreTin\": {\n" +
                    "      \"slagColor\": \"f3e7df\",\n" +
                    "      \"langKey\": [\n" +
                    "        \"minefantasyreforged:tile.tin_ore\"\n" +
                    "      ],\n" +
                    "      \"output\": [\n" +
                    "        \"minefantasyreforged:tin_ingot\"\n" +
                    "      ],\n" +
                    "      \"bloomYieldMinMax\": [\n" +
                    "        1,\n" +
                    "        3\n" +
                    "      ],\n" +
                    "      \"slagBloomYieldMinMax\": [\n" +
                    "        0,\n" +
                    "        2\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"oreSilver\": {\n" +
                    "      \"slagColor\": \"f3f7ff\",\n" +
                    "      \"langKey\": [\n" +
                    "        \"minefantasyreforged:tile.silver_ore\"\n" +
                    "      ],\n" +
                    "      \"output\": [\n" +
                    "        \"minefantasyreforged:silver_ingot\"\n" +
                    "      ],\n" +
                    "      \"bloomYieldMinMax\": [\n" +
                    "        1,\n" +
                    "        3\n" +
                    "      ],\n" +
                    "      \"slagBloomYieldMinMax\": [\n" +
                    "        0,\n" +
                    "        2\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  }\n" +
                    "}");
            writer.flush();

            writer.close();
        }
        catch(IOException e) {
        }
    }

    public static void initWoodCompat(FMLPreInitializationEvent event) {
        try {
            String path = "pyrotech/";
            File file = new File(event.getModConfigurationDirectory(), path + "core.compat.Wood-Custom.json");
            file.deleteOnExit();
            PrintWriter writer = new PrintWriter(file);
            writer.println("{\n" +
                    "  \"__comments\": [\n" +
                    "    \"WARNING: All changes should be made to the file with the name Custom\",\n" +
                    "    \"in the title. Changes made to the Generated file will be overwritten.\",\n" +
                    "    \"\",\n" +
                    "    \"This file defines input and output pairs for auto-generating recipes\",\n" +
                    "    \"for the Chopping Block.\",\n" +
                    "    \"\",\n" +
                    "    \"Entries are in the format (input): (output)\",\n" +
                    "    \"Entry item strings are in the format: (domain):(path):(meta)\"\n" +
                    "  ],\n" +
                    "  \"entries\": {\n" +
                    "    \"minecraft:log2:0\": \"minecraft:planks:4\",\n" +
                    "    \"minecraft:log2:1\": \"minecraft:planks:5\",\n" +
                    "    \"minecraft:log:0\": \"minecraft:planks:0\",\n" +
                    "    \"minecraft:log:1\": \"minecraft:planks:1\",\n" +
                    "    \"minecraft:log:2\": \"minecraft:planks:2\",\n" +
                    "    \"minecraft:log:3\": \"minecraft:planks:3\",\n" +
                    "    \"minefantasyreforged:log_yew\": \"minefantasyreforged:yew_planks\",\n" +
                    "    \"minefantasyreforged:log_ironbark\": \"minefantasyreforged:ironbark_planks\",\n" +
                    "    \"minefantasyreforged:log_ebony\": \"minefantasyreforged:ebony_planks\",\n" +
                    "    \"biomesoplenty:log_0:0\": \"biomesoplenty:planks_0:0\",\n" +
                    "    \"biomesoplenty:log_0:1\": \"biomesoplenty:planks_0:1\",\n" +
                    "    \"biomesoplenty:log_0:10\": \"biomesoplenty:planks_1:2\",\n" +
                    "    \"biomesoplenty:log_0:11\": \"biomesoplenty:planks_1:3\",\n" +
                    "    \"biomesoplenty:log_0:12\": \"biomesoplenty:planks_1:4\",\n" +
                    "    \"biomesoplenty:log_0:13\": \"biomesoplenty:planks_1:5\",\n" +
                    "    \"biomesoplenty:log_0:14\": \"biomesoplenty:planks_1:6\",\n" +
                    "    \"biomesoplenty:log_0:15\": \"biomesoplenty:planks_1:7\",\n" +
                    "    \"biomesoplenty:log_0:2\": \"biomesoplenty:planks_0:2\",\n" +
                    "    \"biomesoplenty:log_0:3\": \"biomesoplenty:planks_0:3\",\n" +
                    "    \"biomesoplenty:log_0:4\": \"biomesoplenty:planks_0:4\",\n" +
                    "    \"biomesoplenty:log_0:5\": \"biomesoplenty:planks_0:5\",\n" +
                    "    \"biomesoplenty:log_0:6\": \"biomesoplenty:planks_0:6\",\n" +
                    "    \"biomesoplenty:log_0:7\": \"biomesoplenty:planks_0:7\",\n" +
                    "    \"biomesoplenty:log_0:8\": \"biomesoplenty:planks_1:0\",\n" +
                    "    \"biomesoplenty:log_0:9\": \"biomesoplenty:planks_1:1\",\n" +
                    "    \"biomesoplenty:planks_0:0\": \"biomesoplenty:wood_slab_0:0\",\n" +
                    "    \"biomesoplenty:planks_0:1\": \"biomesoplenty:wood_slab_0:1\",\n" +
                    "    \"biomesoplenty:planks_0:10\": \"biomesoplenty:wood_slab_1:2\",\n" +
                    "    \"biomesoplenty:planks_0:11\": \"biomesoplenty:wood_slab_1:3\",\n" +
                    "    \"biomesoplenty:planks_0:12\": \"biomesoplenty:wood_slab_1:4\",\n" +
                    "    \"biomesoplenty:planks_0:13\": \"biomesoplenty:wood_slab_1:5\",\n" +
                    "    \"biomesoplenty:planks_0:14\": \"biomesoplenty:wood_slab_1:6\",\n" +
                    "    \"biomesoplenty:planks_0:15\": \"biomesoplenty:wood_slab_1:7\",\n" +
                    "    \"biomesoplenty:planks_0:2\": \"biomesoplenty:wood_slab_0:2\",\n" +
                    "    \"biomesoplenty:planks_0:3\": \"biomesoplenty:wood_slab_0:3\",\n" +
                    "    \"biomesoplenty:planks_0:4\": \"biomesoplenty:wood_slab_0:4\",\n" +
                    "    \"biomesoplenty:planks_0:5\": \"biomesoplenty:wood_slab_0:5\",\n" +
                    "    \"biomesoplenty:planks_0:6\": \"biomesoplenty:wood_slab_0:6\",\n" +
                    "    \"biomesoplenty:planks_0:7\": \"biomesoplenty:wood_slab_0:7\",\n" +
                    "    \"biomesoplenty:planks_0:8\": \"biomesoplenty:wood_slab_1:0\",\n" +
                    "    \"biomesoplenty:planks_0:9\": \"biomesoplenty:wood_slab_1:1\",\n" +
                    "    \"minecraft:planks:0\": \"minecraft:wooden_slab:0\",\n" +
                    "    \"minecraft:planks:1\": \"minecraft:wooden_slab:1\",\n" +
                    "    \"minecraft:planks:2\": \"minecraft:wooden_slab:2\",\n" +
                    "    \"minecraft:planks:3\": \"minecraft:wooden_slab:3\",\n" +
                    "    \"minecraft:planks:4\": \"minecraft:wooden_slab:4\",\n" +
                    "    \"minecraft:planks:5\": \"minecraft:wooden_slab:5\",\n" +
                    "    \"minefantasyreforged:ebony_planks:0\": \"minefantasyreforged:ebony_planks_slab:0\",\n" +
                    "    \"minefantasyreforged:ironbark_planks:0\": \"minefantasyreforged:ironbark_planks_slab:0\",\n" +
                    "    \"minefantasyreforged:refined_planks:0\": \"minefantasyreforged:refined_planks_slab:0\",\n" +
                    "    \"minefantasyreforged:yew_planks:0\": \"minefantasyreforged:yew_planks_slab:0\",\n" +
                    "    \"rustic:planks:0\": \"rustic:olive_slab_item:0\",\n" +
                    "    \"rustic:planks:1\": \"rustic:ironwood_slab_item:0\"\n" +
                    "  }\n" +
                    "}");
            writer.flush();

            writer.close();
        }
        catch(IOException e) {
        }
    }

    public static void initRecipes(FMLPreInitializationEvent event) {
        /*try {
            String path = "MineFantasyReforged/custom/recipes/anvil_recipes/";
            File file = new File(event.getModConfigurationDirectory(), path +  "AlchemyPedestal" + ".json");
            file.deleteOnExit();
            PrintWriter writer = new PrintWriter(file);
            writer.println(PyroFantasyIOInit.ALCHEMYPEDESTAL);
            writer.flush();

            file = new File(event.getModConfigurationDirectory(), path +  "AlchemyTablet" + ".json");
            file.deleteOnExit();
            writer = new PrintWriter(file);
            writer.println(PyroFantasyIOInit.ALCHEMYTABLET);
            writer.flush();

            writer.close();
        }
        catch(IOException e) {
        }*/
    }

}
