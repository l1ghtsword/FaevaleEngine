package ca.lightnet.FaevaleEngine.libraries.utilities;

import java.util.HashMap;
import java.util.Map;

public final class DefaultConfig {

    public static Map<String,Object> getDefaults(String componentName) {
        Map<String,Object> defaults = new HashMap<>();
        switch (componentName) {
            case ("BlockBreakComponent") :
                defaults.put("allowAFK",false);
                defaults.put("defaultTimer",100);
                defaults.put("defaultPlaceholder","BEDROCK");
                defaults.put("properties", new String[] {
                        "ALLIUM,100,AIR",
                        "ATTACHED_MELON_STEM,300,AIR",
                        "ATTACHED_PUMPKIN_STEM,460,AIR",
                        "AZURE_BLUET,100,AIR",
                        "BAMBOO,100,AIR",
                        "BEE_NEST,1200,AIR",
                        "BEEHIVE,1200,AIR",
                        "BEETROOTS,600,AIR",
                        "BLUE_ORCHID,100,AIR",
                        "BRAIN_CORAL_WALL_FAN,100,AIR",
                        "BROWN_MUSHROOM,200,AIR",
                        "BUBBLE_CORAL_WALL_FAN,100,AIR",
                        "CACTUS,600,AIR",
                        "CARROTS,600,AIR",
                        "CARVED_PUMPKIN,460,AIR",
                        "CHORUS_FLOWER,2400,AIR",
                        "CHORUS_FRUIT,2400,AIR",
                        "COBWEB,300,AIR",
                        "COCOA,1200,AIR",
                        "CORNFLOWER,100,AIR",
                        "CRIMSON_FUNGUS,3600,AIR",
                        "CRIMSON_ROOTS,100,AIR",
                        "DANDELION,100,AIR",
                        "DEAD_BRAIN_CORAL_WALL_FAN,100,AIR",
                        "DEAD_BUBBLE_CORAL_WALL_FAN,100,AIR",
                        "DEAD_BUSH,100,AIR",
                        "DEAD_FIRE_CORAL_WALL_FAN,100,AIR",
                        "DEAD_HORN_CORAL_WALL_FAN,100,AIR",
                        "DEAD_TUBE_CORAL_WALL_FAN,100,AIR",
                        "DECORATED_POT,300,AIR",
                        "END_CRYSTAL,18000,AIR",
                        "FERN,100,AIR",
                        "FIRE_CORAL_WALL_FAN,100,AIR",
                        "GLOW_BERRIES,1200,AIR",
                        "GLOW_LICHEN,100,AIR",
                        "GRASS,100,AIR",
                        "HANGING_ROOTS,100,AIR",
                        "HORN_CORAL_WALL_FAN,100,AIR",
                        "KELP,100,AIR",
                        "KELP_PLANT,100,AIR",
                        "LARGE_FERN,100,AIR",
                        "LILAC,100,AIR",
                        "LILY_OF_THE_VALLEY,100,AIR",
                        "LILY_PAD,100,AIR",
                        "MELON,300,AIR",
                        "MELON_STEM,300,AIR",
                        "NETHER_SPROUTS,100,AIR",
                        "NETHER_WART,1200,AIR",
                        "ORANGE_TULIP,100,AIR",
                        "OXEYE_DAISY,100,AIR",
                        "PEONY,100,AIR",
                        "PINK_PETALS,100,AIR",
                        "PINK_TULIP,100,AIR",
                        "POPPY,100,AIR",
                        "POTATOES,800,AIR",
                        "PUMPKIN,460,AIR",
                        "PUMPKIN_STEM,460,AIR",
                        "RED_MUSHROOM,200,AIR",
                        "RED_TULIP,100,AIR",
                        "ROSE_BUSH,100,AIR",
                        "SCULK_CATALYST,1200,AIR",
                        "SCULK_SENSOR,1200,AIR",
                        "SCULK_SHRIEKER,1200,AIR",
                        "SEA_PICKLE,100,AIR",
                        "SEAGRASS,100,AIR",
                        "SPORE_BLOSSOM,100,AIR",
                        "SUGAR_CANE,800,AIR",
                        "SUNFLOWER,100,AIR",
                        "SWEET_BERRIES,300,AIR",
                        "SWEET_BERRY_BUSH,100,AIR",
                        "TALL_GRASS,100,AIR",
                        "TALL_SEAGRASS,100,AIR",
                        "TORCHFLOWER,100,AIR",
                        "TORCHFLOWER_CROP,100,AIR",
                        "TUBE_CORAL_WALL_FAN,100,AIR",
                        "TWISTING_VINES,100,AIR",
                        "TWISTING_VINES_PLANT,100,AIR",
                        "VINE,800,AIR",
                        "WARPED_FUNGUS,3600,AIR",
                        "WARPED_ROOTS,100,AIR",
                        "WEEPING_VINES,100,AIR",
                        "WEEPING_VINES_PLANT,100,AIR",
                        "WHEAT,100,AIR",
                        "WHITE_TULIP,100,AIR",
                        "WITHER_ROSE,100,AIR",
                        "ACACIA_LEAVES,600,BEDROCK",
                        "ACACIA_LOG,600,BEDROCK",
                        "ACACIA_PLANKS,600,BEDROCK",
                        "ACACIA_SAPLING,600,BEDROCK",
                        "ACACIA_WOOD,600,BEDROCK",
                        "AMETHYST_CLUSTER,900,BEDROCK",
                        "ANCIENT_DEBRIS,4800,BEDROCK",
                        "ANDESITE,140,BEDROCK",
                        "AZALEA,6000,BEDROCK",
                        "AZALEA_LEAVES,6000,BEDROCK",
                        "BAMBOO_BLOCK,100,BEDROCK",
                        "BAMBOO_MOSAIC,100,BEDROCK",
                        "BAMBOO_PLANKS,100,BEDROCK",
                        "BAMBOO_SAPLING,100,BEDROCK",
                        "BASALT,900,BEDROCK",
                        "BIRCH_LEAVES,200,BEDROCK",
                        "BIRCH_LOG,200,BEDROCK",
                        "BIRCH_PLANKS,200,BEDROCK",
                        "BIRCH_SAPLING,200,BEDROCK",
                        "BIRCH_WOOD,200,BEDROCK",
                        "BLACK_CONCRETE,140,BEDROCK",
                        "BLACK_CONCRETE_POWDER,200,BEDROCK",
                        "BLACK_GLAZED_TERRACOTTA,360,BEDROCK",
                        "BLACK_TERRACOTTA,360,BEDROCK",
                        "BLACKSTONE,900,BEDROCK",
                        "BLUE_CONCRETE,140,BEDROCK",
                        "BLUE_CONCRETE_POWDER,200,BEDROCK",
                        "BLUE_GLAZED_TERRACOTTA,360,BEDROCK",
                        "BLUE_ICE,100,BEDROCK",
                        "BLUE_TERRACOTTA,360,BEDROCK",
                        "BONE_BLOCK,100,BEDROCK",
                        "BRAIN_CORAL,100,BEDROCK",
                        "BRAIN_CORAL_BLOCK,100,BEDROCK",
                        "BRAIN_CORAL_FAN,100,BEDROCK",
                        "BRICKS,100,BEDROCK",
                        "BROWN_CONCRETE,140,BEDROCK",
                        "BROWN_CONCRETE_POWDER,200,BEDROCK",
                        "BROWN_GLAZED_TERRACOTTA,360,BEDROCK",
                        "BROWN_MUSHROOM_BLOCK,200,BEDROCK",
                        "BROWN_TERRACOTTA,360,BEDROCK",
                        "BUBBLE_CORAL,100,BEDROCK",
                        "BUBBLE_CORAL_BLOCK,100,BEDROCK",
                        "BUBBLE_CORAL_FAN,100,BEDROCK",
                        "CALCITE,140,BEDROCK",
                        "CHERRY_LEAVES,1400,BEDROCK",
                        "CHERRY_LOG,1400,BEDROCK",
                        "CHERRY_PLANKS,1400,BEDROCK",
                        "CHERRY_SAPLING,1400,BEDROCK",
                        "CHERRY_WOOD,1400,BEDROCK",
                        "CHISELED_DEEPSLATE,600,BEDROCK",
                        "CHISELED_NETHER_BRICKS,900,BEDROCK",
                        "CHISELED_POLISHED_BLACKSTONE,900,BEDROCK",
                        "CHISELED_QUARTZ_BLOCK,2400,BEDROCK",
                        "CHISELED_RED_SANDSTONE,360,BEDROCK",
                        "CHISELED_SANDSTONE,300,BEDROCK",
                        "CHISELED_STONE_BRICKS,200,BEDROCK",
                        "CLAY,100,BEDROCK",
                        "COAL_ORE,200,BEDROCK",
                        "COARSE_DIRT,100,BEDROCK",
                        "COBBLED_DEEPSLATE,600,BEDROCK",
                        "COBBLESTONE,100,BEDROCK",
                        "COPPER_ORE,140,BEDROCK",
                        "CRACKED_DEEPSLATE_BRICKS,600,BEDROCK",
                        "CRACKED_DEEPSLATE_TILES,600,BEDROCK",
                        "CRACKED_NETHER_BRICKS,900,BEDROCK",
                        "CRACKED_POLISHED_BLACKSTONE_BRICKS,900,BEDROCK",
                        "CRACKED_STONE_BRICKS,200,BEDROCK",
                        "CRIMSON_HYPHAE,2400,BEDROCK",
                        "CRIMSON_NYLIUM,100,BEDROCK",
                        "CRIMSON_PLANKS,2400,BEDROCK",
                        "CRIMSON_STEM,2400,BEDROCK",
                        "CRYING_OBSIDIAN,760,BEDROCK",
                        "CUT_RED_SANDSTONE,360,BEDROCK",
                        "CUT_SANDSTONE,300,BEDROCK",
                        "CYAN_CONCRETE,140,BEDROCK",
                        "CYAN_CONCRETE_POWDER,200,BEDROCK",
                        "CYAN_GLAZED_TERRACOTTA,360,BEDROCK",
                        "CYAN_TERRACOTTA,360,BEDROCK",
                        "DARK_OAK_LEAVES,900,BEDROCK",
                        "DARK_OAK_LOG,900,BEDROCK",
                        "DARK_OAK_PLANKS,900,BEDROCK",
                        "DARK_OAK_SAPLING,900,BEDROCK",
                        "DARK_OAK_WOOD,900,BEDROCK",
                        "DARK_PRISMARINE,1200,BEDROCK",
                        "DEAD_BRAIN_CORAL,100,BEDROCK",
                        "DEAD_BRAIN_CORAL_BLOCK,100,BEDROCK",
                        "DEAD_BRAIN_CORAL_FAN,100,BEDROCK",
                        "DEAD_BUBBLE_CORAL,100,BEDROCK",
                        "DEAD_BUBBLE_CORAL_BLOCK,100,BEDROCK",
                        "DEAD_BUBBLE_CORAL_FAN,100,BEDROCK",
                        "DEAD_FIRE_CORAL,100,BEDROCK",
                        "DEAD_FIRE_CORAL_BLOCK,100,BEDROCK",
                        "DEAD_FIRE_CORAL_FAN,100,BEDROCK",
                        "DEAD_HORN_CORAL,100,BEDROCK",
                        "DEAD_HORN_CORAL_BLOCK,100,BEDROCK",
                        "DEAD_HORN_CORAL_FAN,100,BEDROCK",
                        "DEAD_TUBE_CORAL,100,BEDROCK",
                        "DEAD_TUBE_CORAL_BLOCK,100,BEDROCK",
                        "DEAD_TUBE_CORAL_FAN,100,BEDROCK",
                        "DEEPSLATE,300,BEDROCK",
                        "DEEPSLATE_BRICKS,600,BEDROCK",
                        "DEEPSLATE_COAL_ORE,300,BEDROCK",
                        "DEEPSLATE_COPPER_ORE,300,BEDROCK",
                        "DEEPSLATE_DIAMOND_ORE,1200,BEDROCK",
                        "DEEPSLATE_EMERALD_ORE,900,BEDROCK",
                        "DEEPSLATE_GOLD_ORE,760,BEDROCK",
                        "DEEPSLATE_IRON_ORE,300,BEDROCK",
                        "DEEPSLATE_LAPIS_ORE,600,BEDROCK",
                        "DEEPSLATE_REDSTONE_ORE,460,BEDROCK",
                        "DEEPSLATE_TILES,300,BEDROCK",
                        "DIAMOND_ORE,1200,BEDROCK",
                        "DIORITE,140,BEDROCK",
                        "DIRT,100,BEDROCK",
                        "DIRT_PATH,100,BEDROCK",
                        "DRIPSTONE_BLOCK,200,BEDROCK",
                        "EMERALD_ORE,760,BEDROCK",
                        "END_STONE,100,BEDROCK",
                        "END_STONE_BRICKS,300,BEDROCK",
                        "FARMLAND,100,BEDROCK",
                        "FIRE_CORAL,100,BEDROCK",
                        "FIRE_CORAL_BLOCK,100,BEDROCK",
                        "FIRE_CORAL_FAN,100,BEDROCK",
                        "FLOWERING_AZALEA,6000,BEDROCK",
                        "FLOWERING_AZALEA_LEAVES,6000,BEDROCK",
                        "FROSTED_ICE,100,BEDROCK",
                        "GILDED_BLACKSTONE,900,BEDROCK",
                        "GLOWSTONE,760,BEDROCK",
                        "GOLD_ORE,760,BEDROCK",
                        "GRANITE,140,BEDROCK",
                        "GRASS_BLOCK,100,BEDROCK",
                        "GRAVEL,100,BEDROCK",
                        "GRAY_CONCRETE,140,BEDROCK",
                        "GRAY_CONCRETE_POWDER,200,BEDROCK",
                        "GRAY_GLAZED_TERRACOTTA,360,BEDROCK",
                        "GRAY_TERRACOTTA,360,BEDROCK",
                        "GREEN_CONCRETE,140,BEDROCK",
                        "GREEN_CONCRETE_POWDER,200,BEDROCK",
                        "GREEN_GLAZED_TERRACOTTA,360,BEDROCK",
                        "GREEN_TERRACOTTA,360,BEDROCK",
                        "HAY_BLOCK,100,BEDROCK",
                        "HORN_CORAL,100,BEDROCK",
                        "HORN_CORAL_BLOCK,100,BEDROCK",
                        "HORN_CORAL_FAN,100,BEDROCK",
                        "ICE,100,BEDROCK",
                        "IRON_ORE,300,BEDROCK",
                        "JUNGLE_LEAVES,400,BEDROCK",
                        "JUNGLE_LOG,400,BEDROCK",
                        "JUNGLE_PLANKS,400,BEDROCK",
                        "JUNGLE_SAPLING,400,BEDROCK",
                        "JUNGLE_WOOD,400,BEDROCK",
                        "LAPIS_ORE,600,BEDROCK",
                        "LIGHT_BLUE_CONCRETE,140,BEDROCK",
                        "LIGHT_BLUE_CONCRETE_POWDER,200,BEDROCK",
                        "LIGHT_BLUE_GLAZED_TERRACOTTA,360,BEDROCK",
                        "LIGHT_BLUE_TERRACOTTA,360,BEDROCK",
                        "LIGHT_GRAY_CONCRETE,140,BEDROCK",
                        "LIGHT_GRAY_CONCRETE_POWDER,200,BEDROCK",
                        "LIGHT_GRAY_GLAZED_TERRACOTTA,360,BEDROCK",
                        "LIGHT_GRAY_TERRACOTTA,360,BEDROCK",
                        "LIME_CONCRETE,140,BEDROCK",
                        "LIME_CONCRETE_POWDER,200,BEDROCK",
                        "LIME_GLAZED_TERRACOTTA,360,BEDROCK",
                        "LIME_TERRACOTTA,360,BEDROCK",
                        "MAGENTA_CONCRETE,140,BEDROCK",
                        "MAGENTA_CONCRETE_POWDER,200,BEDROCK",
                        "MAGENTA_GLAZED_TERRACOTTA,360,BEDROCK",
                        "MAGENTA_TERRACOTTA,360,BEDROCK",
                        "MAGMA_BLOCK,460,BEDROCK",
                        "MANGROVE_LEAVES,300,BEDROCK",
                        "MANGROVE_LOG,300,BEDROCK",
                        "MANGROVE_PLANKS,300,BEDROCK",
                        "MANGROVE_PROPAGULE,300,BEDROCK",
                        "MANGROVE_WOOD,300,BEDROCK",
                        "MOSS_BLOCK,100,BEDROCK",
                        "MOSSY_COBBLESTONE,140,BEDROCK",
                        "MOSSY_STONE_BRICKS,200,BEDROCK",
                        "MUD,100,BEDROCK",
                        "MUD_BRICKS,100,BEDROCK",
                        "MUSHROOM_STEM,200,BEDROCK",
                        "MYCELIUM,100,BEDROCK",
                        "NETHER_BRICKS,900,BEDROCK",
                        "NETHER_GOLD_ORE,760,BEDROCK",
                        "NETHER_QUARTZ_ORE,2400,BEDROCK",
                        "NETHERRACK,100,BEDROCK",
                        "OAK_LEAVES,100,BEDROCK",
                        "OAK_LOG,100,BEDROCK",
                        "OAK_PLANKS,100,BEDROCK",
                        "OAK_SAPLING,100,BEDROCK",
                        "OAK_WOOD,100,BEDROCK",
                        "OBSIDIAN,760,BEDROCK",
                        "ORANGE_CONCRETE,140,BEDROCK",
                        "ORANGE_CONCRETE_POWDER,200,BEDROCK",
                        "ORANGE_GLAZED_TERRACOTTA,360,BEDROCK",
                        "ORANGE_TERRACOTTA,360,BEDROCK",
                        "PACKED_ICE,100,BEDROCK",
                        "PACKED_MUD,100,BEDROCK",
                        "PINK_CONCRETE,140,BEDROCK",
                        "PINK_CONCRETE_POWDER,200,BEDROCK",
                        "PINK_GLAZED_TERRACOTTA,360,BEDROCK",
                        "PINK_TERRACOTTA,360,BEDROCK",
                        "PODZOL,100,BEDROCK",
                        "POINTED_DRIPSTONE,200,BEDROCK",
                        "POLISHED_ANDESITE,140,BEDROCK",
                        "POLISHED_BASALT,140,BEDROCK",
                        "POLISHED_BLACKSTONE,900,BEDROCK",
                        "POLISHED_BLACKSTONE_BRICKS,900,BEDROCK",
                        "POLISHED_DEEPSLATE,300,BEDROCK",
                        "POLISHED_DIORITE,140,BEDROCK",
                        "POLISHED_GRANITE,140,BEDROCK",
                        "POWDER_SNOW,100,BEDROCK",
                        "PRISMARINE,1200,BEDROCK",
                        "PRISMARINE_BRICKS,1200,BEDROCK",
                        "PURPLE_CONCRETE,140,BEDROCK",
                        "PURPLE_CONCRETE_POWDER,200,BEDROCK",
                        "PURPLE_GLAZED_TERRACOTTA,360,BEDROCK",
                        "PURPLE_TERRACOTTA,360,BEDROCK",
                        "PURPUR_BLOCK,900,BEDROCK",
                        "PURPUR_PILLAR,900,BEDROCK",
                        "QUARTZ_BLOCK,2400,BEDROCK",
                        "QUARTZ_BRICKS,2400,BEDROCK",
                        "QUARTZ_PILLAR,2400,BEDROCK",
                        "RED_CONCRETE,140,BEDROCK",
                        "RED_CONCRETE_POWDER,200,BEDROCK",
                        "RED_GLAZED_TERRACOTTA,360,BEDROCK",
                        "RED_MUSHROOM_BLOCK,200,BEDROCK",
                        "RED_NETHER_BRICKS,900,BEDROCK",
                        "RED_SAND,360,BEDROCK",
                        "RED_SANDSTONE,360,BEDROCK",
                        "RED_TERRACOTTA,360,BEDROCK",
                        "REDSTONE_ORE,460,BEDROCK",
                        "REINFORCED_DEEPSLATE,1200,BEDROCK",
                        "ROOTED_DIRT,100,BEDROCK",
                        "SAND,300,BEDROCK",
                        "SANDSTONE,300,BEDROCK",
                        "SCULK,100,BEDROCK",
                        "SHROOMLIGHT,100,BEDROCK",
                        "SMOOTH_BASALT,140,BEDROCK",
                        "SMOOTH_QUARTZ,2400,BEDROCK",
                        "SMOOTH_RED_SANDSTONE,140,BEDROCK",
                        "SMOOTH_SANDSTONE,140,BEDROCK",
                        "SMOOTH_STONE,100,BEDROCK",
                        "SNOW,100,BEDROCK",
                        "SNOW_BLOCK,100,BEDROCK",
                        "SOUL_SAND,100,BEDROCK",
                        "SOUL_SOIL,100,BEDROCK",
                        "SPRUCE_LEAVES,140,BEDROCK",
                        "SPRUCE_LOG,140,BEDROCK",
                        "SPRUCE_PLANKS,140,BEDROCK",
                        "SPRUCE_SAPLING,140,BEDROCK",
                        "SPRUCE_WOOD,140,BEDROCK",
                        "STONE,100,BEDROCK",
                        "STONE_BRICKS,200,BEDROCK",
                        "STRIPPED_ACACIA_LOG,600,BEDROCK",
                        "STRIPPED_ACACIA_WOOD,600,BEDROCK",
                        "STRIPPED_BAMBOO_BLOCK,100,BEDROCK",
                        "STRIPPED_BIRCH_LOG,200,BEDROCK",
                        "STRIPPED_BIRCH_WOOD,200,BEDROCK",
                        "STRIPPED_CHERRY_LOG,1400,BEDROCK",
                        "STRIPPED_CHERRY_WOOD,1400,BEDROCK",
                        "STRIPPED_CRIMSON_HYPHAE,2400,BEDROCK",
                        "STRIPPED_CRIMSON_STEM,2400,BEDROCK",
                        "STRIPPED_DARK_OAK_LOG,900,BEDROCK",
                        "STRIPPED_DARK_OAK_WOOD,900,BEDROCK",
                        "STRIPPED_JUNGLE_LOG,400,BEDROCK",
                        "STRIPPED_JUNGLE_WOOD,400,BEDROCK",
                        "STRIPPED_MANGROVE_LOG,300,BEDROCK",
                        "STRIPPED_MANGROVE_WOOD,300,BEDROCK",
                        "STRIPPED_OAK_LOG,100,BEDROCK",
                        "STRIPPED_OAK_WOOD,100,BEDROCK",
                        "STRIPPED_SPRUCE_LOG,140,BEDROCK",
                        "STRIPPED_SPRUCE_WOOD,140,BEDROCK",
                        "STRIPPED_WARPED_HYPHAE,4800,BEDROCK",
                        "STRIPPED_WARPED_STEM,4800,BEDROCK",
                        "SUSPICIOUS_SAND,300,BEDROCK",
                        "TERRACOTTA,360,BEDROCK",
                        "TUBE_CORAL,100,BEDROCK",
                        "TUBE_CORAL_BLOCK,100,BEDROCK",
                        "TUBE_CORAL_FAN,100,BEDROCK",
                        "TUFF,100,BEDROCK",
                        "WARPED_HYPHAE,4800,BEDROCK",
                        "WARPED_NYLIUM,100,BEDROCK",
                        "WARPED_PLANKS,4800,BEDROCK",
                        "WARPED_STEM,4800,BEDROCK",
                        "WHITE_CONCRETE,140,BEDROCK",
                        "WHITE_CONCRETE_POWDER,200,BEDROCK",
                        "WHITE_GLAZED_TERRACOTTA,360,BEDROCK",
                        "WHITE_TERRACOTTA,360,BEDROCK",
                        "YELLOW_CONCRETE,140,BEDROCK",
                        "YELLOW_CONCRETE_POWDER,200,BEDROCK",
                        "YELLOW_GLAZED_TERRACOTTA,360,BEDROCK",
                        "YELLOW_TERRACOTTA,360,BEDROCK"
                });
                break;

            default :
                defaults.put("NO_DEFAULTS",true);
        }
        return defaults;
    }

}
