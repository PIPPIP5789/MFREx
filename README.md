# MineFantasy: Reforged Extras

In order to use this mod, simply load it alongside MineFantasy: Reforged, then add your JSON files to the config to add the recipe into the game. A sample JSON file for each config is listed below.

## Anvil

{
  "type": "ShapedAnvilRecipes",
  "skill": "artisanry",
  "research": "",
  "tool_type": "hammer",
  "is_tool_recipe": false,
  "anvil_tier": 0,
  "recipe_time": 8,
  "recipe_hammer": 0,
  "output_hot": true,
  "pattern": [
    "B B   ",
    " B    ",
    "      ",
    "      "
  ],
  "key": {
    "B": {
      "item": "minefantasyreforged:bar"
    }
  },
  "result": {
    "item": "minecraft:bucket"
  }
}

## Blast Furnace

{
  "type": "BlastFurnaceRecipe",
  "input": {
    "item": "minecraft:sand"
  },
  "result": {
    "item": "minefantasyreforged:salt"
  }
}

## Carpenter

{
  "type": "crafting_shaped",
  "skill": "none",
  "research": "",
  "tool_type": "hammer",
  "is_tool_recipe": false,
  "block_tier": -1,
  "craft_time": 10,
  "tool_tier": -1,
  "experience": 0,
  "sound": "minefantasyreforged:block.hammercarpenter",
  "pattern": [
    "SS  ",
    "SSS ",
    " S  ",
    "    "
  ],
  "key": {
    "S": {
      "item": "minecraft:stone",
      "data": 0
    }
  },
  "result": {
    "item": "minefantasyreforged:anvil_stone"
  }
}

## Crucible

{
  "name": "bronze2",
  "type": "AlloyRecipe",
  "crucible_tier": 0,
  "pattern": "CTT",
  "key": {
    "C": {
    "nbt": "{mf_custom_materials:{main_material:\"copper\"}}",
    "item": "minefantasyreforged:bar"
  },
    "T": {
      "nbt": "{mf_custom_materials:{main_material:\"tin\"}}",
      "item": "minefantasyreforged:bar"
    }
  },
  "result": {
    "nbt": "{mf_custom_materials:{main_material:\"bronze\"}}",
    "item": "minefantasyreforged:bar"
  }
}

## Quern

{
  "type": "QuernRecipe",
  "tier": 0,

  "input": {
    "item": "minecraft:sand"
  },
  "result": {
    "item": "minefantasyreforged:salt"
  }

}

## Metal Registry (metal_types.json)

{
    "mod": "minefantasyreforged",
    "metals": [
        {
            "name": "tin",
            "oreDictList": "ingotTin",
            "properties": {
                "tier": 0,
                "durability": 1.0,
                "flexibility": 0.5,
                "sharpness": 0.0,
                "hardness": 1.5,
                "resistance": 10,
                "density": 2.0,
                "melting_point": 900,
                "rarity": -1,
                "craft_tier": -1,
                "craft_time_modifier": 2.0,
                "unbreakable": false
            },
            "armour_stats": {
                "cutting": 1.0,
                "blunt": 1.0,
                "piercing": 1.0
            },
            "color": {
                "red": 164,
                "green": 177,
                "blue": 177
            }
        }
    ]
}

## Ore Registry (ores.json)

{
  "mod": "minefantasyreforged",
  "ores": [
    {
      "ore": {
        "item": "minefantasyreforged:ore_copper"
      },
      "bar": "Copper"
    }]
}

## Wood Registry (wood_types.json)

{
    "mod": "minefantasyreforged",
    "woods": [
        {
            "name": "scrap_wood",
            "inputItem": "",
            "inputItemMeta": 0,
            "properties": {
                "tier": 0,
                "hardness": 0.1,
                "durability": 0.50,
                "flexibility": 0.5,
                "resistance": 10,
                "density": 0.5,
                "rarity": 0,
                "craft_tier": 0,
                "craft_time_modifier": 2.0
            },
            "color": {
                "red": 100,
                "green": 95,
                "blue": 80
            }
        }
    ]
}
