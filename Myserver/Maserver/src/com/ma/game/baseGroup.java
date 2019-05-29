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

	protected ArrayList<baseblock> basegroups_blocks_baseblock;// ��ǰ���ϰ����ķ����б�
	protected int basegroups_gridpostionx_int;// ��ǰ�����ڵ�ͼ�е�����x

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

	protected int basegroups_gridpostiony_int;// ��ǰ�����ڵ�ͼ�е�����y

	protected int groups_rotatestate_int;// ��ǰ��ת״̬
	protected int groups_shape_int;// ��ǰͼ����״
	/// <summary>
	/// �вι��캯��
	/// </summary>
	/// <param name="shape">ͼ������</param>
	/// <param name="rotate">ͼ����ת״̬</param>
	/// <param name="gridposx">ͼ���ڵ�ͼ�е�����x</param>
	/// <param name="gridposy">ͼ���ڵ�ͼ�е�����y</param>
	/// <param name="map">��ǰ��ͼ��</param>

	public baseGroup(int shape, int rotate, int gridposx, int gridposy, game_map map, game_manager manager) {
		basegroups_blocks_baseblock = new ArrayList<baseblock>();
		basegroups_gridpostionx_int = gridposx;
		basegroups_gridpostiony_int = gridposy;
		groups_shape_int = shape;
		InitGroup();
		groups_rotatestate_int = rotate;
		elsfk_basegroups_onrotate(rotate);
		// ���ϸմ����ж��ܷ����䣬���������Ϸ����
		if (!elsfk_groups_isvalidgridpos(map)) {
			manager.setGameOver(true);
		}

		elsfk_groups_updatagrid(map);
		this.manager = manager;

	}

	/// <summary>
	/// �����ʼ������
	/// </summary>
	public abstract void InitGroup();

	/// <summary>
	/// ������ת����
	/// </summary>
	public abstract void elsfk_basegroups_onrotate(int rotatenum);

	/// <summary>
	/// �жϵ�ǰͼ���Ƿ��ܹ�����
	/// </summary>
	/// <param name="map">�������ڵ�ͼ</param>
	/// <returns>�������������䷵��false</returns>
	public boolean elsfk_groups_isvalidgridpos(game_map map) {
		boolean canMove = true;
		// ֻҪ��һ��С���鲻������ͷ���false
		for (baseblock child : basegroups_blocks_baseblock) {
			canMove = child.elsfk_baseblock_isvalidgridpos(map);
			if (!canMove)
				break;
		}

		return canMove;
	}

	/// <summary>
	/// ͼ�ζ���������Ʒ���
	/// </summary>
	/// <param name="state">�궨�尴��״̬</param>
	/// <param name="map">�������ڵ�ͼ</param>
	public void onclick(int state, game_map map) {
		// ���������
		if (state == game_define.STATE_LEFT) {
			// �ƶ�ÿ��С����
			elsfk_groups_movegroup(-1, 0);
			// �������Ϸ������ز���
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_groups_movegroup(1, 0);
			}
			elsfk_groups_updatagrid(map);// ���µ�ͼ
		} else if (state == game_define.STATE_RIGHT)// �������Ҽ�
		{
			// �ƶ�ÿ��С����
			elsfk_groups_movegroup(1, 0);
			// �������Ϸ������ز���
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_groups_movegroup(-1, 0);
			}
			elsfk_groups_updatagrid(map);// ���µ�ͼ
		} else if (state == game_define.STATE_DOWN)// �������¼����߶�ʱ������Ҫ��
		{
			// �ƶ�ÿ��С����
			elsfk_groups_movegroup(0, -1);
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_groups_movegroup(0, 1);// �������Ϸ������ز���
				elsfk_groups_updatagrid(map);
				map.elsfk_globalData_deletefullrows();// ɾ�����˵���
				if (!game_globalData.g_elsfk_globalData_isgameover)// �����Ϸû�����ͼ�������ͼ��
					manager.Init(game_define.DEFPOSX, game_define.DEFPOSY, map);
			}
			elsfk_groups_updatagrid(map);
		} else if (state == game_define.STATE_UP)// �������ϼ�
		{
			elsfk_basegroups_onrotate(++groups_rotatestate_int);// ��תͼ��
			if (!elsfk_groups_isvalidgridpos(map)) {
				elsfk_basegroups_onrotate(--groups_rotatestate_int);
			}
			elsfk_groups_updatagrid(map);
		}

	}

	/// <summary>
	/// ���ݵ�ǰͼ�ζ�����µ�ͼ����
	/// </summary>
	/// <param name="map">�������ڵ�ͼ</param>
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
	/// ɾ��С����
	/// </summary>
	/// <param name="block">С����</param>
	/// <param name="map">�������ڵ�ͼ</param>
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
