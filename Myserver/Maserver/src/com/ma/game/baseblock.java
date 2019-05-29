package com.ma.game;

public class baseblock {
	    private int baseblock_positionx_int;//方块相对于集合位置x
	    public int getBaseblock_positionx_int() {
			return baseblock_positionx_int;
		}

		public void setBaseblock_positionx_int(int baseblock_positionx_int) {
			this.baseblock_positionx_int = baseblock_positionx_int;
		}

		public int getBaseblock_positiony_int() {
			return baseblock_positiony_int;
		}

		public void setBaseblock_positiony_int(int baseblock_positiony_int) {
			this.baseblock_positiony_int = baseblock_positiony_int;
		}

		public int getBaseblock_gridpositionx_int() {
			return baseblock_gridpositionx_int;
		}

		public void setBaseblock_gridpositionx_int(int baseblock_gridpositionx_int) {
			this.baseblock_gridpositionx_int = baseblock_gridpositionx_int;
		}
	    private int baseblock_positiony_int;//方块相对于集合位置y
	    public int getBaseblock_gridpositiony_int() {
			return baseblock_gridpositiony_int;
		}

		public void setBaseblock_gridpositiony_int(int baseblock_gridpositiony_int) {
   this.baseblock_gridpositiony_int = baseblock_gridpositiony_int;
		}
		
	    private int baseblock_gridpositionx_int;//方块在地图中的x位置
	 
	    private int baseblock_gridpositiony_int;//方块在地图中的位置y
	   
	    private baseGroup baseblock_parent_groups;//包含当前方块的集合
	   

	    
	    public baseGroup getBaseblock_parent_groups() {
	 			return baseblock_parent_groups;
	 		}

		public void setBaseblock_parent_groups(baseGroup baseblock_parent_groups) {
			this.baseblock_parent_groups = baseblock_parent_groups;
		}

		/// <summary> 
	    /// 设置小方块在集合中的相对位置和地图位置
	    /// </summary>
	    /// <param name="def">宏定义中的位置</param>
	    /// <param name="group">小方块所属的集合</param>
	    public void elsfk_baseblock_setstartpos(int def, baseGroup group)
	    {
	    	
	    
	        baseblock_positionx_int = (def % 5) - 2;
	        baseblock_positiony_int = (def / 5)-2;
	        baseblock_gridpositionx_int = group.getBasegroups_gridpostionx_int()+ baseblock_positionx_int;
	        baseblock_gridpositiony_int = group.getBasegroups_gridpostiony_int() + baseblock_positiony_int;
	    }

	    /// <summary> 
	    /// 判断方块在地图中的位置是否合法
	    /// </summary>
	    /// <param name="map">小方块所在地图</param>
	    ///<returns>合法返回true</returns>
	    public boolean elsfk_baseblock_isvalidgridpos(game_map map)
	    {
	        //如果超出边界
	        if (!map.map_insideBorder(this))
	        {
	            return false;
	        }
	        //如果不能继续移动
	        if (map.map_baseblocks[baseblock_gridpositionx_int][baseblock_gridpositiony_int] != null &&
	                map.map_baseblocks[baseblock_gridpositionx_int] [baseblock_gridpositiony_int].getBaseblock_parent_groups() != baseblock_parent_groups)
	        {
	            return false;
	        }

	        return true;
	    }
	    /// <summary> 
	    /// 在地图中更新方块位置
	    /// </summary>
	    /// <param name="map">小方块所在地图</param>
	    public void elsfk_baseblock_updatagrid(game_map map)
	    {
	        map.map_baseblocks[baseblock_gridpositionx_int][ baseblock_gridpositiony_int] = this;
	    }
	    /// <summary> 
	    /// 在地图中清空方块位置
	    /// </summary>
	    /// <param name="map">小方块所在地图</param>
	    public void elsfk_baseblock_removelast(game_map map)
	    {
	        map.map_baseblocks[baseblock_gridpositionx_int][baseblock_gridpositiony_int] = null;
	    }
	    /// <summary> 
	    /// 方块响应按键方法
	    /// </summary>
	    /// <param name="state">当前按键状态</param>
	    /// <param name="map">小方块所在地图</param>
	    public void elsfk_baseblock_onkeydown(int state, game_map map)
	    {
	        //按下向左键
	        if (state == game_define.STATE_LEFT)
	        {
	            elsfk_baseblock_removelast(map);
	            elsfk_baseblock_move(-1,0);//图形左移
	            //如果能够左移更新图像，如果不能就返回操作
	            if (!elsfk_baseblock_isvalidgridpos(map))
	                elsfk_baseblock_move(1, 0);
	        }
	        //按下向右键
	        else if (state == game_define.STATE_RIGHT)
	        {
	            elsfk_baseblock_removelast(map);
	            elsfk_baseblock_move(1, 0);//图形右移
	            //如果能够右移更新图像，如果不能就返回操作
	            if (!elsfk_baseblock_isvalidgridpos(map))
	            {
	                elsfk_baseblock_move(-1, 0);
	            }
	        }
	        //按下向上键
	        else if (state == game_define.STATE_UP)
	        {
	        }
	        //按下向下键
	        else if (state == game_define.STATE_DOWN)
	        {
	            elsfk_baseblock_removelast(map);
	            elsfk_baseblock_move(0, -1);//图形坐标下移
	            //如果能够下移更新图像，如果不能就返回操作
	            if (!elsfk_baseblock_isvalidgridpos(map))
	            {
	                elsfk_baseblock_move(0, 1);
	            }

	        }
	    }
	    /// <summary> 
	    /// 方块响移动
	    /// </summary>
	    /// <param name="posx">方块移动的向量x</param>
	    /// <param name="posy">方块移动的向量y</param>
	    public void elsfk_baseblock_move(int posx,int posy)
	    {
	        baseblock_gridpositionx_int += posx;
	        baseblock_gridpositiony_int += posy;
	    }
	    /// <summary> 
	    /// 设置方块在集合中的相对坐标并计算方块的世界坐标
	    /// </summary>
	    /// <param name="posx">方块在集合中的相对坐标x</param>
	    /// <param name="posy">方块在集合中的相对坐标y</param>
	    public void elsfk_baseblock_setposition(int posx,int posy)
	    {
	       int oldpositionx = baseblock_positionx_int;
	        int oldpositiony = baseblock_positiony_int;
	        baseblock_positionx_int = posx;
	        baseblock_positiony_int = posy;
	        baseblock_gridpositionx_int = baseblock_gridpositionx_int - oldpositionx + baseblock_positionx_int;
	        baseblock_gridpositiony_int = baseblock_gridpositiony_int - oldpositiony + baseblock_positiony_int;
	    }
}
