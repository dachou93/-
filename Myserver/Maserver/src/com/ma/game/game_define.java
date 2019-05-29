package com.ma.game;

public class game_define {

	
	  public static String BLOCKPATH = "Prefabs/BLOCK";//方块预制件路径

	    public static int DEFPOSX = 5;//初始生成点坐标x
	    public static int DEFPOSY = 16; //初始生成点坐标y


	    public static int STATE_LEFT = 0;//向左
	    public static int STATE_RIGHT = 1;//向右
	    public static int STATE_DOWN = 2;//向下
	    public static int STATE_UP = 3;//向上

	    //5 5 数组12为中心点
	    public static int[] OGROUP1 = new int[] { 7, 8, 12, 13 };//O形状状态1

	    public static int[] IGROUP1 = new int[] { 17, 12, 7, 2 };//I形状状态1
	    public static int[] IGROUP2 = new int[] { 11, 12, 13, 14 };//I形状状态2

	    public static int[] TGROUP1 = new int[] { 11, 12, 13, 17 };//T形状状态1
	    public static int[] TGROUP2 = new int[] { 17, 12, 7, 13 };//T形状状态2
	    public static int[] TGROUP3 = new int[] { 13, 12, 11, 7 };//T形状状态3
	    public static int[] TGROUP4 = new int[] { 7, 12, 17, 11 };//T形状状态4

	    public static int[] LGROUP1 = new int[] { 10, 11, 12, 17 };//L形状状态1
	    public static int[] LGROUP2 = new int[] { 22, 17, 12, 13 };//L形状状态2
	    public static int[] LGROUP3 = new int[] { 14,13, 12, 7 };//L形状状态3
	    public static int[] LGROUP4 = new int[] { 2, 7, 12, 11 };//L形状状态4

	    public static int[] JGROUP1 = new int[] { 14, 13, 12,17 };//J形状状态1
	    public static int[] JGROUP2 = new int[] { 2, 7, 12, 13 };//J形状状态2
	    public static int[] JGROUP3 = new int[] { 10, 11, 12,7 };//J形状状态3
	    public static int[] JGROUP4 = new int[] { 22, 17, 12, 11 };//J形状状态4

	    public static int[] ZGROUP1 = new int[] { 11, 12, 7, 8 };//Z形状状态1
	    public static int[] ZGROUP2 = new int[] { 17, 12, 11, 6 };//Z形状状态2

	    public static int[] SGROUP1 = new int[] { 13, 12, 7, 6 };//S形状状态1
	    public static int[] SGROUP2 = new int[] { 7, 12, 11, 16 };//S形状状态2
}
