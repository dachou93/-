package com.ma.game;

import java.util.HashMap;

import io.netty.channel.ChannelId;

public class game_globalData {

	public static int g_elsfk_globalData_width = 10;//��ͼ���
    public static int g_elsfk_globalData_hight = 20;//��ͼ����
    public static boolean  g_elsfk_globalData_isgameover = false;//��Ϸ�Ƿ����
    public static game_map g_elsfk_globalData_map=new game_map();//��ǰ��Ϸ��ͼ����
    
    
    public static HashMap<ChannelId, game_main> gameMap=new HashMap<ChannelId, game_main>();
}
