package com.ma.game;

public class baseblock {
	    private int baseblock_positionx_int;//��������ڼ���λ��x
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
	    private int baseblock_positiony_int;//��������ڼ���λ��y
	    public int getBaseblock_gridpositiony_int() {
			return baseblock_gridpositiony_int;
		}

		public void setBaseblock_gridpositiony_int(int baseblock_gridpositiony_int) {
   this.baseblock_gridpositiony_int = baseblock_gridpositiony_int;
		}
		
	    private int baseblock_gridpositionx_int;//�����ڵ�ͼ�е�xλ��
	 
	    private int baseblock_gridpositiony_int;//�����ڵ�ͼ�е�λ��y
	   
	    private baseGroup baseblock_parent_groups;//������ǰ����ļ���
	   

	    
	    public baseGroup getBaseblock_parent_groups() {
	 			return baseblock_parent_groups;
	 		}

		public void setBaseblock_parent_groups(baseGroup baseblock_parent_groups) {
			this.baseblock_parent_groups = baseblock_parent_groups;
		}

		/// <summary> 
	    /// ����С�����ڼ����е����λ�ú͵�ͼλ��
	    /// </summary>
	    /// <param name="def">�궨���е�λ��</param>
	    /// <param name="group">С���������ļ���</param>
	    public void elsfk_baseblock_setstartpos(int def, baseGroup group)
	    {
	    	
	    
	        baseblock_positionx_int = (def % 5) - 2;
	        baseblock_positiony_int = (def / 5)-2;
	        baseblock_gridpositionx_int = group.getBasegroups_gridpostionx_int()+ baseblock_positionx_int;
	        baseblock_gridpositiony_int = group.getBasegroups_gridpostiony_int() + baseblock_positiony_int;
	    }

	    /// <summary> 
	    /// �жϷ����ڵ�ͼ�е�λ���Ƿ�Ϸ�
	    /// </summary>
	    /// <param name="map">С�������ڵ�ͼ</param>
	    ///<returns>�Ϸ�����true</returns>
	    public boolean elsfk_baseblock_isvalidgridpos(game_map map)
	    {
	        //��������߽�
	        if (!map.map_insideBorder(this))
	        {
	            return false;
	        }
	        //������ܼ����ƶ�
	        if (map.map_baseblocks[baseblock_gridpositionx_int][baseblock_gridpositiony_int] != null &&
	                map.map_baseblocks[baseblock_gridpositionx_int] [baseblock_gridpositiony_int].getBaseblock_parent_groups() != baseblock_parent_groups)
	        {
	            return false;
	        }

	        return true;
	    }
	    /// <summary> 
	    /// �ڵ�ͼ�и��·���λ��
	    /// </summary>
	    /// <param name="map">С�������ڵ�ͼ</param>
	    public void elsfk_baseblock_updatagrid(game_map map)
	    {
	        map.map_baseblocks[baseblock_gridpositionx_int][ baseblock_gridpositiony_int] = this;
	    }
	    /// <summary> 
	    /// �ڵ�ͼ����շ���λ��
	    /// </summary>
	    /// <param name="map">С�������ڵ�ͼ</param>
	    public void elsfk_baseblock_removelast(game_map map)
	    {
	        map.map_baseblocks[baseblock_gridpositionx_int][baseblock_gridpositiony_int] = null;
	    }
	    /// <summary> 
	    /// ������Ӧ��������
	    /// </summary>
	    /// <param name="state">��ǰ����״̬</param>
	    /// <param name="map">С�������ڵ�ͼ</param>
	    public void elsfk_baseblock_onkeydown(int state, game_map map)
	    {
	        //���������
	        if (state == game_define.STATE_LEFT)
	        {
	            elsfk_baseblock_removelast(map);
	            elsfk_baseblock_move(-1,0);//ͼ������
	            //����ܹ����Ƹ���ͼ��������ܾͷ��ز���
	            if (!elsfk_baseblock_isvalidgridpos(map))
	                elsfk_baseblock_move(1, 0);
	        }
	        //�������Ҽ�
	        else if (state == game_define.STATE_RIGHT)
	        {
	            elsfk_baseblock_removelast(map);
	            elsfk_baseblock_move(1, 0);//ͼ������
	            //����ܹ����Ƹ���ͼ��������ܾͷ��ز���
	            if (!elsfk_baseblock_isvalidgridpos(map))
	            {
	                elsfk_baseblock_move(-1, 0);
	            }
	        }
	        //�������ϼ�
	        else if (state == game_define.STATE_UP)
	        {
	        }
	        //�������¼�
	        else if (state == game_define.STATE_DOWN)
	        {
	            elsfk_baseblock_removelast(map);
	            elsfk_baseblock_move(0, -1);//ͼ����������
	            //����ܹ����Ƹ���ͼ��������ܾͷ��ز���
	            if (!elsfk_baseblock_isvalidgridpos(map))
	            {
	                elsfk_baseblock_move(0, 1);
	            }

	        }
	    }
	    /// <summary> 
	    /// �������ƶ�
	    /// </summary>
	    /// <param name="posx">�����ƶ�������x</param>
	    /// <param name="posy">�����ƶ�������y</param>
	    public void elsfk_baseblock_move(int posx,int posy)
	    {
	        baseblock_gridpositionx_int += posx;
	        baseblock_gridpositiony_int += posy;
	    }
	    /// <summary> 
	    /// ���÷����ڼ����е�������겢���㷽�����������
	    /// </summary>
	    /// <param name="posx">�����ڼ����е��������x</param>
	    /// <param name="posy">�����ڼ����е��������y</param>
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
