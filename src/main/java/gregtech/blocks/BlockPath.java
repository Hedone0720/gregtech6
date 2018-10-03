/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.blocks;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import gregapi.block.BlockBaseMeta;
import gregapi.block.IBlockOnWalkOver;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPath extends BlockBaseMeta implements IBlockOnWalkOver {
	public BlockPath(String aUnlocalised) {
		super(null, aUnlocalised, Material.grass, soundTypeGrass, 2, Textures.BlockIcons.PATHS);
		LH.add(getUnlocalizedName()+ ".0.name", "Grass Path");
		LH.add(getUnlocalizedName()+ ".1.name", "Aether Grass Path");
		LH.add(getUnlocalizedName()+ ".2.name", "Loamy Grass Path");
		LH.add(getUnlocalizedName()+ ".3.name", "Sandy Grass Path");
		LH.add(getUnlocalizedName()+ ".4.name", "Silty Grass Path");
		setBlockBounds(0, 0, 0, 1, PIXELS_NEG[1], 1);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		switch(aMeta) {
		case  1: return new ArrayListNoNulls<>(F, IL.AETHER_Dirt.get(1));
		case  2: return new ArrayListNoNulls<>(F, IL.BoP_Dirt_Loamy.get(1));
		case  3: return new ArrayListNoNulls<>(F, IL.BoP_Dirt_Sandy.get(1));
		case  4: return new ArrayListNoNulls<>(F, IL.BoP_Dirt_Silty.get(1));
		default: return new ArrayListNoNulls<>(F, ST.make(Blocks.dirt, 1, 0));
		}
	}
	
	@Override public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {aEntity.motionX *= 1.1; aEntity.motionZ *= 1.1;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return (SIDES_TOP[aSide]?Textures.BlockIcons.PATH:(SIDES_BOTTOM[aSide]?Textures.BlockIcons.DIRTS:Textures.BlockIcons.PATHS)[aMeta % 16]).getIcon(0);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool (World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1);}
	@Override public boolean doesWalkSpeed(short aMeta) {return T;}
	@Override public boolean canCreatureSpawn(int aMeta) {return F;}
	@Override public boolean canSilkHarvest() {return F;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.grass.getBlockHardness(aWorld, aX, aY, aZ) * 2;}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.grass.getExplosionResistance(null) * 1.5F;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide];}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean isNormalCube() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
}
