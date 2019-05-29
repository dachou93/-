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
	  private baseGroup manager_group_basegroups;//��ǰ���϶���
	  private boolean isGameOver;
	    
     private game_map map;
	    /// <summary>
	    /// �޲ι��캯��
	    /// </summary>
	    public game_manager()
	    {
	    	isGameOver=false;
	    }
	    /// <summary>
	    /// ��Ϸ�������ʼ��
	    /// </summary>
	    /// <param name="defposx">�����ڵ�ͼ�е�����x</param>
	    /// <param name="defposy">�����ڵ�ͼ�е�����y</param>
	    /// <param name="map">���ڵ�ͼ</param>
	    public void Init(int defposx, int defposy, game_map map)
	    {
	        Random ran = new Random();
	        int shape = ran.nextInt(7)+1;
	        int rotate = ran.nextInt(3);
	        this.map=map;
	        manager_group_basegroups = manager_groupfactory(shape, rotate, defposx, defposy, map);//��ʼ�����ϵ�ͼ�κ���ת�Լ������ڵ�ͼ�е�����

	    }
	    /// <summary>
	    /// ���϶�����������
	    /// </summary>
	    /// <param name="shape">����ͼ��</param>
	    /// <param name="rotate">������ת״̬</param
	    /// <param name = "defposx" > �����ڵ�ͼ�е�����x </ param >
	    /// <param name="defposy">�����ڵ�ͼ�е�����y</param>
	    /// <param name="map">���ڵ�ͼ</param>
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
	    /// ��Ϸ������Ӧ��������
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
