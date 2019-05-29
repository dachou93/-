package com.ma.game;

import java.util.Random;

import com.ma.game.block.game_igroup;
import com.ma.game.block.game_jgroup;
import com.ma.game.block.game_lgroup;
import com.ma.game.block.game_ogroup;
import com.ma.game.block.game_sgroup;
import com.ma.game.block.game_tgroup;
import com.ma.game.block.game_zgroup;

public class game_manager {
	  private baseGroup manager_group_basegroups;//当前集合对象
	  private boolean isGameOver;
	    
     private game_map map;
	    /// <summary>
	    /// 无参构造函数
	    /// </summary>
	    public game_manager()
	    {
	    	isGameOver=false;
	    }
	    /// <summary>
	    /// 游戏管理类初始化
	    /// </summary>
	    /// <param name="defposx">集合在地图中的坐标x</param>
	    /// <param name="defposy">集合在地图中的坐标y</param>
	    /// <param name="map">所在地图</param>
	    public void Init(int defposx, int defposy, game_map map)
	    {
	        Random ran = new Random();
	        int shape = ran.nextInt(7)+1;
	        int rotate = ran.nextInt(3);
	        this.map=map;
	        manager_group_basegroups = manager_groupfactory(shape, rotate, defposx, defposy, map);//初始化集合的图形和旋转以及集合在地图中的坐标

	    }
	    /// <summary>
	    /// 集合对象生产工厂
	    /// </summary>
	    /// <param name="shape">集合图形</param>
	    /// <param name="rotate">集合旋转状态</param
	    /// <param name = "defposx" > 集合在地图中的坐标x </ param >
	    /// <param name="defposy">集合在地图中的坐标y</param>
	    /// <param name="map">所在地图</param>
	    private baseGroup manager_groupfactory(int shape, int rotate, int defposx, int defposy,game_map map)
	    {
	        switch (shape)
	        {
	            case 1:
	                return new game_igroup(shape, rotate, defposx, defposy, map,this);
	            case 2:
	                return new game_tgroup(shape, rotate, defposx, defposy, map,this);
	            case 3:
	                return new game_jgroup(shape, rotate, defposx, defposy, map,this);
	            case 4:
	                return new game_lgroup(shape, rotate, defposx, defposy, map,this);
	            case 5:
	                return new game_zgroup(shape, rotate, defposx, defposy, map,this);
	            case 6:
	                return new game_sgroup(shape, rotate, defposx, defposy, map,this);
	            case 7:
	                return new game_ogroup(shape, rotate, defposx, defposy, map,this);
	            default:
	                return null;
	        }

	    }
	    /// <summary>
	    /// 游戏管理响应按键方法
	    /// </summary>
	    public void OnClickkeyboard(int clicktype)
	    {
	    	//todo
	        if (manager_group_basegroups != null)
	            manager_group_basegroups.onclick(clicktype, map);
	    }
		public boolean isGameOver() {
			return isGameOver;
		}
		public void setGameOver(boolean isGameOver) {
			this.isGameOver = isGameOver;
		}

}
