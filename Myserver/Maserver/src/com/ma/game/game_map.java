package com.ma.game;

import java.util.ArrayList;
import java.util.List;

public class game_map {
	public baseblock[][] map_baseblocks;// 当前游戏地图数组

	/// <summary>
	/// 无参构造函数
	/// </summary>
	public game_map() {
		map_baseblocks = new baseblock[game_globalData.g_elsfk_globalData_width][game_globalData.g_elsfk_globalData_hight];
	}

	/// <summary>
	/// 判断小方块是否在地图中
	/// </summary>
	/// <param name="block">用来判断的方块</param>
	public boolean map_insideBorder(baseblock block) {
		return (block.getBaseblock_gridpositionx_int() >= 0
				&& block.getBaseblock_gridpositionx_int() < game_globalData.g_elsfk_globalData_width
				&& block.getBaseblock_gridpositiony_int() >= 0);
	}

	/// <summary>
	/// 删除所有满行并将上层的降下
	/// </summary>
	public void elsfk_globalData_deletefullrows() {
		for (int y = 0; y < game_globalData.g_elsfk_globalData_hight;) {
			if (elsfk_globalData_isrowfull(y)) {
				elsfk_globalData_deleterow(y);
				elsfk_globalData_decreaserowabove(y + 1);
			} else
				y++;
		}
	}

	/// <summary>
	/// 判断当前行是否满了
	/// </summary>
	/// <param name="y">行数索引</param>
	/// <returns></returns>
	private boolean elsfk_globalData_isrowfull(int y) {
		for (int i = 0; i < game_globalData.g_elsfk_globalData_width; i++) {
			if (map_baseblocks[i][y] == null)
				return false;
		}
		return true;
	}

	/// <summary>
	/// 按传入的行索引删除当前行方块
	/// </summary>
	/// <param name="y">行数索引</param>
	private void elsfk_globalData_deleterow(int y) {
		for (int i = 0; i < game_globalData.g_elsfk_globalData_width; i++) {
			map_baseblocks[i][y].getBaseblock_parent_groups().elsfk_groups_removeblock(map_baseblocks[i][y], this); // 将集合中的小方块从集合中删除
		}
	}

	/// <summary>
	/// 将传入层数及以上层的方块下移
	/// </summary>
	/// <param name="y">行数索引</param>
	private void elsfk_globalData_decreaserowabove(int y) {
		for (int i = y; i < game_globalData.g_elsfk_globalData_hight; i++) {
			elsfk_globalData_decreaserow(i);
		}
	}

	/// <summary>
	/// 按传入的列索引将格子同一列上方的方块下移并清空格子
	/// </summary>
	/// <param name="y">列索引</param>
	private void elsfk_globalData_decreaserow(int y) {
		for (int x = 0; x < game_globalData.g_elsfk_globalData_width; x++) {
			if (map_baseblocks[x][y] != null) {
				map_baseblocks[x][y - 1] = map_baseblocks[x][y];
				map_baseblocks[x][y - 1].elsfk_baseblock_move(0, -1);
				map_baseblocks[x][y] = null;
			}
		}
	}

	public List<Integer> getMap() {
		List<Integer> m=new ArrayList<Integer>() ;
		for (int i = 0; i < game_globalData.g_elsfk_globalData_width; i++) {
			for (int j = 0; j < game_globalData.g_elsfk_globalData_hight; j++) {
				if (map_baseblocks[i][j] == null)
					 m.add(0);
				else
					 m.add(1);
			}
		}
		return  m;
	}
}
