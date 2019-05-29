package com.ma.game.block;

import com.ma.game.baseGroup;
import com.ma.game.baseblock;
import com.ma.game.game_define;
import com.ma.game.game_manager;
import com.ma.game.game_map;

public class game_zgroup extends baseGroup{

    public game_zgroup(int shape,int rotate, int gridposx, int gridposy, game_map map,game_manager manager)
    {
        super(shape,rotate,gridposx,gridposy,map,manager);
    }

	@Override
	public void InitGroup() {
		 for (int i = 0; i < 4; i++)
	        {
	            basegroups_blocks_baseblock.add(new baseblock());
	            basegroups_blocks_baseblock.get(i).setBaseblock_parent_groups ( this);
	            basegroups_blocks_baseblock.get(i).elsfk_baseblock_setstartpos(game_define.ZGROUP1[i], this);
	        }
		
	}

	@Override
	public void elsfk_basegroups_onrotate(int rotatenum) {
	
		
		//根据旋转计数来设置方块坐标
        if (rotatenum % 2 == 1)
        {
            for (int i = 0; i < 4; i++)
            {
                basegroups_blocks_baseblock.get(i).elsfk_baseblock_setstartpos(game_define.ZGROUP2[i],this);
            }
        }
        else
        {
            for (int i = 0; i < 4; i++)
            {
                basegroups_blocks_baseblock.get(i).elsfk_baseblock_setstartpos(game_define.ZGROUP1[i],this);
            }
        }
        groups_rotatestate_int = rotatenum;
       

		
	}

}
