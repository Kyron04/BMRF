package com.bmrf;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = BMRFMod.MODID)
public class ModBlocks {
    public static final int COUNT = 432;
    public static final Block[] BLOCKS = new Block[COUNT];

    public static final CreativeTabs TAB = new CreativeTabs("BMRF") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BLOCKS[0]);
        }

        @Override
        public String getTabLabel() {
            return "BMRF";
        }
    };

    static {
        for (int i = 0; i < COUNT; i++) {
            int n = i + 1;
            String name = "bmrf" + n;
            Block block = new Block(Material.ROCK)
                    .setHardness(1.5F)
                    .setResistance(10.0F)
                    .setCreativeTab(TAB);
            block.setRegistryName(BMRFMod.MODID, name);
            block.setTranslationKey(BMRFMod.MODID + "." + name);
            BLOCKS[i] = block;
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BLOCKS);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Block block : BLOCKS) {
            ItemBlock item = new ItemBlock(block);
            item.setRegistryName(block.getRegistryName());
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        for (Block block : BLOCKS) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, 0,
                    new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
    }
}
