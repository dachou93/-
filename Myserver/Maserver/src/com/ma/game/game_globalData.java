package com.ma.game;

import java.util.HashMap;

import io.netty.channel.ChannelId;

public class game_globalData {

	public static int g_elsfk_globalData_width = 10;//地图宽度
    public static int g_elsfk_globalData_hight = 20;//地图长度
    public static boolean  g_elsfk_globalData_isgameover = false;//游戏是否结束
    public static game_map g_elsfk_globalData_map=new game_map();//当前游戏地图数组
    
    
    public static HashMap<ChannelId, game_main> gameMap=new HashMap<ChannelId, game_main>();
}
