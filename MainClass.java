import java.util.Scanner; 
public class MainClass{ 
	public static void main (String args[]){ 
	Scanner _key = new Scanner(System.in);
boolean x;
int i;
x =true;
System.out.println("\n teste 1 \n");
while (x==true) {
x =false;}

System.out.println("\n teste 2 \n");
i =0;
for(i = 0;i<10;i = i + 1) {
System.out.println(i);}

i =0;
System.out.println("\n teste 3 \n");
while (i+1<10) {
System.out.println(i);i =i + 1;}

System.out.println("\n teste 4 \n");
do {
System.out.println(i);i =i - 1;} while (i>0);

	}}