package com.ma.game.block;

import com.ma.game.baseGroup;
import com.ma.game.baseblock;
import com.ma.game.game_define;
import com.ma.game.game_manager;
import com.ma.game.game_map;

public class game_ogroup extends baseGroup{

	
    public game_ogroup(int shape,int rotate, int gridposx, int gridposy, game_map map,game_manager manager)
    {
        super(shape,rotate,gridposx,gridposy,map,manager);
    }
	    /// <summary>
	    /// ��д���ϵ���ת����
	    /// </summary>
	    public  void elsfk_basegroups_onrotate(int rotatenum)
	    {

	    }
	    /// <summary>
	    /// ���ϵĳ�ʼ��
	    /// </summary>
	    public  void InitGroup()
	    {
	    	
	        for (int i = 0; i < 4; i++)
	        {
	            basegroups_blocks_baseblock.add(new baseblock());
	            basegroups_blocks_baseblock.get(i).setBaseblock_parent_groups ( this);
	            basegroups_blocks_baseblock.get(i).elsfk_baseblock_setstartpos(game_define.OGROUP1[i], this);
	        }
	        
	    }
}
