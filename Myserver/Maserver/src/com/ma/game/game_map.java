package com.ma.game;

import java.util.ArrayList;
import java.util.List;

public class game_map {
	public baseblock[][] map_baseblocks;// ��ǰ��Ϸ��ͼ����

	/// <summary>
	/// �޲ι��캯��
	/// </summary>
	public game_map() {
		map_baseblocks = new baseblock[game_globalData.g_elsfk_globalData_width][game_globalData.g_elsfk_globalData_hight];
	}

	/// <summary>
	/// �ж�С�����Ƿ��ڵ�ͼ��
	/// </summary>
	/// <param name="block">�����жϵķ���</param>
	public boolean map_insideBorder(baseblock block) {
		return (block.getBaseblock_gridpositionx_int() >= 0
				&& block.getBaseblock_gridpositionx_int() < game_globalData.g_elsfk_globalData_width
				&& block.getBaseblock_gridpositiony_int() >= 0);
	}

	/// <summary>
	/// ɾ���������в����ϲ�Ľ���
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
	/// �жϵ�ǰ���Ƿ�����
	/// </summary>
	/// <param name="y">��������</param>
	/// <returns></returns>
	private boolean elsfk_globalData_isrowfull(int y) {
		for (int i = 0; i < game_globalData.g_elsfk_globalData_width; i++) {
			if (map_baseblocks[i][y] == null)
				return false;
		}
		return true;
	}

	/// <summary>
	/// �������������ɾ����ǰ�з���
	/// </summary>
	/// <param name="y">��������</param>
	private void elsfk_globalData_deleterow(int y) {
		for (int i = 0; i < game_globalData.g_elsfk_globalData_width; i++) {
			map_baseblocks[i][y].getBaseblock_parent_groups().elsfk_groups_removeblock(map_baseblocks[i][y], this); // �������е�С����Ӽ�����ɾ��
		}
	}

	/// <summary>
	/// ��������������ϲ�ķ�������
	/// </summary>
	/// <param name="y">��������</param>
	private void elsfk_globalData_decreaserowabove(int y) {
		for (int i = y; i < game_globalData.g_elsfk_globalData_hight; i++) {
			elsfk_globalData_decreaserow(i);
		}
	}

	/// <summary>
	/// �������������������ͬһ���Ϸ��ķ������Ʋ���ո���
	/// </summary>
	/// <param name="y">������</param>
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
