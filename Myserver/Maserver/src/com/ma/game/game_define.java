package com.ma.game;

public class game_define {

	
	  public static String BLOCKPATH = "Prefabs/BLOCK";//����Ԥ�Ƽ�·��

	    public static int DEFPOSX = 5;//��ʼ���ɵ�����x
	    public static int DEFPOSY = 16; //��ʼ���ɵ�����y


	    public static int STATE_LEFT = 0;//����
	    public static int STATE_RIGHT = 1;//����
	    public static int STATE_DOWN = 2;//����
	    public static int STATE_UP = 3;//����

	    //5 5 ����12Ϊ���ĵ�
	    public static int[] OGROUP1 = new int[] { 7, 8, 12, 13 };//O��״״̬1

	    public static int[] IGROUP1 = new int[] { 17, 12, 7, 2 };//I��״״̬1
	    public static int[] IGROUP2 = new int[] { 11, 12, 13, 14 };//I��״״̬2

	    public static int[] TGROUP1 = new int[] { 11, 12, 13, 17 };//T��״״̬1
	    public static int[] TGROUP2 = new int[] { 17, 12, 7, 13 };//T��״״̬2
	    public static int[] TGROUP3 = new int[] { 13, 12, 11, 7 };//T��״״̬3
	    public static int[] TGROUP4 = new int[] { 7, 12, 17, 11 };//T��״״̬4

	    public static int[] LGROUP1 = new int[] { 10, 11, 12, 17 };//L��״״̬1
	    public static int[] LGROUP2 = new int[] { 22, 17, 12, 13 };//L��״״̬2
	    public static int[] LGROUP3 = new int[] { 14,13, 12, 7 };//L��״״̬3
	    public static int[] LGROUP4 = new int[] { 2, 7, 12, 11 };//L��״״̬4

	    public static int[] JGROUP1 = new int[] { 14, 13, 12,17 };//J��״״̬1
	    public static int[] JGROUP2 = new int[] { 2, 7, 12, 13 };//J��״״̬2
	    public static int[] JGROUP3 = new int[] { 10, 11, 12,7 };//J��״״̬3
	    public static int[] JGROUP4 = new int[] { 22, 17, 12, 11 };//J��״״̬4

	    public static int[] ZGROUP1 = new int[] { 11, 12, 7, 8 };//Z��״״̬1
	    public static int[] ZGROUP2 = new int[] { 17, 12, 11, 6 };//Z��״״̬2

	    public static int[] SGROUP1 = new int[] { 13, 12, 7, 6 };//S��״״̬1
	    public static int[] SGROUP2 = new int[] { 7, 12, 11, 16 };//S��״״̬2
}
