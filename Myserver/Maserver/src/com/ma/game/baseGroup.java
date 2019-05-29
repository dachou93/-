package com.ma.game;

import java.util.ArrayList;
import java.util.List;

public abstract class baseGroup {

	private game_manager manager;

	public game_manager getManager() {
		return manager;
	}

	public void setManager(game_manager manager) {
		this.manager = manager;
	}

	protected ArrayList<baseblock> basegroups_blocks_baseblock;// 当前集合包含的方块列表
	protected int basegroups_gridpostionx_int;// 当前集合在地图中的坐标x

	public List<baseblock> getBasegroups_blocks_baseblock() {
		return basegroups_blocks_baseblock;
	}

	public void setBasegroups_blocks_baseblock(ArrayList<baseblock> basegroups_blocks_baseblock) {
		this.basegroups_blocks_baseblock = basegroups_blocks_baseblock;
	}

	public int getBasegroups_gridpostionx_int() {
		return basegroups_gridpostionx_int;
	}

	public void setBasegroups_gridpostionx_int(int basegroups_gridpostionx_int) {
		this.basegroups_gridpostionx_int = basegroups_gridpostionx_int;
	}

	public int getBasegroups_gridpostiony_int() {
		return basegroups_gridpostiony_int;
	}

	public void setBasegroups_gridpostiony_int(int basegroups_gridpostiony_int) {
		this.basegroups_gridpostiony_int = basegroups_gridpostiony_int;
	}

	public int getGroups_rotatestate_int() {
		return groups_rotatestate_int;
	}

	public void setGroups_rotatestate_int(int groups_rotatestate_int) {
		this.groups_rotatestate_int = groups_rotatestate_int;
	}

	public int getGroups_shape_int() {
		return groups_shape_int;
	}

	public void setGroups_shape_int(int groups_shape_int) {
		this.groups_shape_int = groups_shape_int;
	}

	protected int basegroups_gridpostiony_int;// 当前集合在地图中的坐标y

	protected int groups_rotatestate_int;// 当前旋转状态
	protected int groups_shape_int;// 当前图形形状
	/// <summary>
	/// 有参构造函数
	/// </summary>
	/// <param name="shape">图形种类</param>
	/// <param name="rotate">图形旋转状态</param>
	/// <param name="gridposx">图形在地图中的坐标x</param>
	/// <param name="gridposy">图形在地图中的坐标y</param>
	/// <param name="map">当前地图类</param>

	public baseGroup(int shape, int rotate, int gridposx, int gridposy, game_map map, game_manager manager) {
		basegroups_blocks_baseblock = new ArrayList<baseblock>();
		basegroups_gridpostionx_int = gridposx;
		basegroups_gridpostiony_int = gridposy;
		groups_shape_int = shape;
		InitGroup();
		groups_rotatestate_int = rotate;
		elsfk_basegroups_onrotate(rotate);
		// 集合刚创建判断能否下落，如果不能游戏结束
		if (!elsfk_groups_isvalidgridpos(map)) {
			manager.setGameOver(true);
		}

		elsfk_groups_updatagrid(map);
		this.manager = manager;

	}

	/// <summary>
	/// 抽象初始化方法
	/// </summary>
	public abstract void InitGroup();

	/// <summary>
	/// 抽象旋转方法
	/// </summary>
	public abstract void elsfk_basegroups_onrotate(int rotatenum);

	/// <summary>
	/// 判断当前图形是否能够下落
	/// </summary>
	/// <param name="map">集合所在地图</param>
	/// <returns>超出及不能下落返回false</returns>
	public boolean elsfk_groups_isvalidgridpos(game_map map) {
		boolean canMove = true;
		// 只要有一个小方块不能下落就返回false
		for (baseblock child : basegroups_blocks_baseblock) {
			canMove = child.elsfk_baseblock_isvalidgridpos(map);
			if (!canMove)
				break;
		}

		return canMove;
	}

	/// <summary>
	/// 图形对象输入控制方法
	/// </summary>
	/// <param name="state">宏定义按键状态</param>
	/// <param name="map">集合所在地图</param>
	public void onclick(int state, game_map map) {
		// 按下向左键
		if (state == game_define.STATE_LEFT) {
			// 移动每个小方块
			elsfk_groups_movegroup(-1, 0);
			// 操作不合法，返回操作
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_groups_movegroup(1, 0);
			}
			elsfk_groups_updatagrid(map);// 更新地图
		} else if (state == game_define.STATE_RIGHT)// 按下向右键
		{
			// 移动每个小方块
			elsfk_groups_movegroup(1, 0);
			// 操作不合法，返回操作
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_groups_movegroup(-1, 0);
			}
			elsfk_groups_updatagrid(map);// 更新地图
		} else if (state == game_define.STATE_DOWN)// 按下向下键或者定时器满足要求
		{
			// 移动每个小方块
			elsfk_groups_movegroup(0, -1);
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_groups_movegroup(0, 1);// 操作不合法，返回操作
				elsfk_groups_updatagrid(map);
				map.elsfk_globalData_deletefullrows();// 删除满了的行
				if (!game_globalData.g_elsfk_globalData_isgameover)// 如果游戏没结束就继续产生图形
					manager.Init(game_define.DEFPOSX, game_define.DEFPOSY, map);
			}
			elsfk_groups_updatagrid(map);
		} else if (state == game_define.STATE_UP)// 按下向上键
		{
			elsfk_basegroups_onrotate(++groups_rotatestate_int);// 旋转图形
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_basegroups_onrotate(--groups_rotatestate_int);
			}
			elsfk_groups_updatagrid(map);
		}

	}

	/// <summary>
	/// 根据当前图形对象更新地图数组
	/// </summary>
	/// <param name="map">集合所在地图</param>
	public void elsfk_groups_updatagrid(game_map map) {
		for (int y = 0; y < game_globalData.g_elsfk_globalData_hight; y++) {
			for (int x = 0; x < game_globalData.g_elsfk_globalData_width; x++) {
				if (map.map_baseblocks[x][y] != null)
					if (map.map_baseblocks[x][y].getBaseblock_parent_groups() == this)
						map.map_baseblocks[x][y] = null;
			}
		}
		for (baseblock block : basegroups_blocks_baseblock) {
			block.elsfk_baseblock_updatagrid(map);
		}
	}

	/// <summary>
	/// 删除小方块
	/// </summary>
	/// <param name="block">小方块</param>
	/// <param name="map">集合所在地图</param>
	public void elsfk_groups_removeblock(baseblock block, game_map map) {
		basegroups_blocks_baseblock.remove(block);
		map.map_baseblocks[block.getBaseblock_gridpositionx_int()][block.getBaseblock_gridpositiony_int()] = null;
	}

	public void elsfk_groups_movegroup(int posx, int posy) {
		for (baseblock child : basegroups_blocks_baseblock) {
			child.elsfk_baseblock_move(posx, posy);
		}

		basegroups_gridpostionx_int += posx;
		basegroups_gridpostiony_int += posy;
	}

}
