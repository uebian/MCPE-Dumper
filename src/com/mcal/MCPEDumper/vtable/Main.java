import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		dump d=new dump("/data/data/com.mojang.minecraftpe/lib/libminecraftpe.so");//lib存在位置
		
		Scanner input = new Scanner(System.in);
		System.out.println("请输入虚表名(_ZTV形式)");
		String classn=input.next();
		
		symbol sym=null;
		section symsec=null;
		System.out.println("寻找中");
		for(section sec:d.elf.sections){
			if(sec.type==2||sec.type==11){
				for(int i=0;i<d.getSymNum(sec);i++){
					symbol sym_=d.getSym(sec,i);
					if(sym_.name.equals(classn)){
						sym=sym_;
						symsec=sec;
						break;
					}
				}
			}
		}
		
		if(sym==null){
			System.out.println("未找到");
			return;
		}
		
		HashMap<Integer,symbol>map=new HashMap<Integer,symbol>();//为了排序
		int c=0;
		
		for(section sec:d.elf.sections){
			if(sec.name.equals(".rel.dyn")){
				for(int i=0;i<d.getRelNum(sec);i++){
					relocation rel=d.getRel(sec,i);
					for(int j=0;j<sym.size/4-2;j++){
						if(sym.value+8+j*4==rel.offset){
							c++;
							symbol vsym=d.getSym(symsec,rel.info>>8);
							System.out.println("已加裁"+c+"个:");
							map.put(rel.offset,vsym);
						}
					}
					if(map.size()==sym.size/4-2){
						break;
					}
				}
			}
		}
		
		System.out.println("共"+c+"个,如下");
		for(int j=0;j<sym.size/4-2;j++){
			if(map.get(sym.value+8+j*4)!=null)System.out.println(map.get(sym.value+8+j*4).name);
		}
		
	}
}
