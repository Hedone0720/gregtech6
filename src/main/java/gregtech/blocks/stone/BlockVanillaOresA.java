/**
 * Copyright (c) 2020 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.blocks.stone;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import gregapi.block.BlockBaseMeta;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVanillaOresA extends BlockBaseMeta {
	public static byte[] HARVEST_LEVELS = {0, 0, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 2, 0, 3, 0};
	public static int[] BURN_LEVELS = {100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0};
	public static float[] HARDNESS_LEVELS = {0.5F, 0.5F, 1.5F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.5F, 1.0F, 1.5F, 1.5F, 1.5F, 0.5F, 2.0F, 0.5F};
	public static OreDictMaterial[] ORE_MATERIALS = {MT.S, MT.Apatite, MT.Ruby, MT.Amber, MT.Amethyst, MT.OREMATS.Galena, MT.OREMATS.Tetrahedrite, MT.OREMATS.Cassiterite, MT.OREMATS.Cooperite, MT.OREMATS.Pentlandite, MT.OREMATS.Scheelite, MT.TiO2, MT.OREMATS.Bastnasite, MT.Graphite, MT.OREMATS.Pitchblende, MT.OREMATS.Borax};
	
	// Vanilla Style GT6 Stone Ores, mainly used for Twilight Forest, so the Ore Magnet doesn't draw too many breaking Ores.
	public BlockVanillaOresA(String aUnlocalised) {
		super(null, aUnlocalised, Material.rock, soundTypeStone, 16, Textures.BlockIcons.VANILLA_ORES_A);
		LH.add(getUnlocalizedName()+  ".0.name", "Sulfur Ore");
		LH.add(getUnlocalizedName()+  ".1.name", "Apatite Ore");
		LH.add(getUnlocalizedName()+  ".2.name", "Ruby Ore");
		LH.add(getUnlocalizedName()+  ".3.name", "Amber Ore");
		LH.add(getUnlocalizedName()+  ".4.name", "Amethyst Ore");
		LH.add(getUnlocalizedName()+  ".5.name", "Galena Ore");
		LH.add(getUnlocalizedName()+  ".6.name", "Tetrahedrite Ore");
		LH.add(getUnlocalizedName()+  ".7.name", "Cassiterite Ore");
		LH.add(getUnlocalizedName()+  ".8.name", "Sheldonite Ore");
		LH.add(getUnlocalizedName()+  ".9.name", "Pentlandite Ore");
		LH.add(getUnlocalizedName()+ ".10.name", "Scheelite Ore");
		LH.add(getUnlocalizedName()+ ".11.name", "Rutile Ore");
		LH.add(getUnlocalizedName()+ ".12.name", "Bastnasite Ore");
		LH.add(getUnlocalizedName()+ ".13.name", "Graphite Ore");
		LH.add(getUnlocalizedName()+ ".14.name", "Pitchblende Ore");
		LH.add(getUnlocalizedName()+ ".15.name", "Borax Ore");
		
		for (int i = 0; i < ORE_MATERIALS.length; i++) OM.reg(ST.make(this, 1, i), OP.oreVanillastone.dat(ORE_MATERIALS[i]));
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		ArrayListNoNulls<ItemStack> rDrops = new ArrayListNoNulls<>();
		switch(aMeta) {
		case  0: case  1: case  2: case  3: case  4:
			rDrops.add(OP.gem .mat(ORE_MATERIALS[aMeta], ORE_MATERIALS[aMeta].mOreMultiplier + (aFortune>0?(RNGSUS.nextInt((1+aFortune)*ORE_MATERIALS[aMeta].mOreMultiplier)):0)));
			break;
		case 13: case 15:
			rDrops.add(OP.dust.mat(ORE_MATERIALS[aMeta], ORE_MATERIALS[aMeta].mOreMultiplier + (aFortune>0?(RNGSUS.nextInt((1+aFortune)*ORE_MATERIALS[aMeta].mOreMultiplier)):0)));
			break;
		default:
			rDrops.add(ST.make(this, 1, aMeta));
			break;
		}
		return rDrops;
	}
	
	@Override
	public int getExpDrop(IBlockAccess aWorld, int aMeta, int aFortune) {
		switch(aMeta) {
		case  0: case  1:          return MathHelper.getRandomIntegerInRange(RNGSUS, 0, 2);
		case 13: case 15:          return MathHelper.getRandomIntegerInRange(RNGSUS, 2, 5);
		case  2: case  3: case  4: return MathHelper.getRandomIntegerInRange(RNGSUS, 3, 7);
		default: return 0;
		}
	}
	
	@Override public boolean useGravity(byte aMeta) {return F;}
	@Override public boolean doesWalkSpeed(byte aMeta) {return F;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean canSilkHarvest(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return T;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return HARVEST_LEVELS[aMeta];}
	@Override public int getFlammability(byte aMeta) {return BURN_LEVELS[aMeta];}
	@Override public int getFireSpreadSpeed(byte aMeta) {return BURN_LEVELS[aMeta];}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.stone.getBlockHardness(aWorld, aX, aY, aZ) * HARDNESS_LEVELS[WD.meta(aWorld, aX, aY, aZ)];}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.stone.getExplosionResistance(null);}
}