package com.ma.game.block;

import com.ma.game.baseGroup;
import com.ma.game.baseblock;
import com.ma.game.game_define;
import com.ma.game.game_manager;
import com.ma.game.game_map;

public class game_igroup extends baseGroup{
	
	  /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="shape">图形种类</param>
    /// <param name="rotate">图形旋转状态</param>
    /// <param name="gridposx">图形在地图中的坐标x</param>
    /// <param name="gridposy">图形在地图中的坐标y</param>
    /// <param name="map">当前地图类</param>
    public game_igroup(int shape,int rotate, int gridposx, int gridposy, game_map map,game_manager manager)
    {
        super(shape,rotate,gridposx,gridposy,map,manager);
    }
    /// <summary>
    /// 重写集合的旋转方法
    /// </summary>
    public  void elsfk_basegroups_onrotate(int rotatenum)
    {
    	
    
        //根据旋转计数来设置方块坐标
        if (rotatenum % 2==1)
        {
            for(int i=0;i<4;i++)
            {
                basegroups_blocks_baseblock.get(i).elsfk_baseblock_setstartpos(game_define.IGROUP2[i],this); 
            }     
        }
        else 
        {
            for (int i = 0; i < 4; i++)
            {
            	basegroups_blocks_baseblock.get(i).elsfk_baseblock_setstartpos(game_define.IGROUP1[i],this);
            }
        }
        groups_rotatestate_int = rotatenum;
      

    }
    /// <summary>
    /// 集合的初始化
    /// </summary>
    public  void InitGroup()
    {
        for (int i = 0; i < 4; i++)
        {
            basegroups_blocks_baseblock.add(new baseblock());
            basegroups_blocks_baseblock.get(i).setBaseblock_parent_groups(this);
            basegroups_blocks_baseblock.get(i).elsfk_baseblock_setstartpos(game_define.IGROUP1[i], this);
        }
    }

}
