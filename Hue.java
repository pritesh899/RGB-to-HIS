import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Hue {

	public static void main(String[] args) throws IOException
	{
		
		BufferedImage image = null;
		File f = null;
		
		try
		{
			f = new File("/Users/priteshratnappagol/Desktop/f_images/landscape.png");
			image = ImageIO.read(f);
			System.out.println("Reading Complete");
		}catch(IOException e){
			System.out.println("Error :"+e);
		}
		int width = image.getWidth();
		int height =image.getHeight();
		for(int y = 0; y< height ; y++)
		{
			for(int x = 0; x< width ; x++)
			{
				int p = image.getRGB(x,y);
				int a = (p>>24)&0xff;
			    int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p&0xff;
				
			
				double num = 0.5*((r-g)+(r-b)) ;
				double deno = ( ((r*r)-(2*r*g)+(g*g)) + ((r-b)*(g-b)) );
				double deno1 = Math.sqrt(deno);
				double deno2 = deno1;
				double h = num/(deno2+0.000001);
				float hue = (float) (Math.acos(h)*(3.14/180));
				double s;
				int  intensity = (int) ((r+g+b)/3);

				if(r==g && g==b)
				{
					s = 0;
					hue = 0;
				}
				else
				{
				
				s = 1 - ((1/(intensity+0.000001)))* (Math.min(r,(Math.min(r,b))));
				if(s < 0.00001)
		            {
		                  s = 0;
		           
		            }
				else if (s>0.9999){
		                  s = 1;
		            }
				
				if(g>=b)
				{
					hue = hue;
				}
				else
				{
				  hue = (float) ((360*3.14)/180 - hue); 
				}
				}
							
				
					r = (int)((hue*180)/3.14);
					g = (int)((hue*180)/3.14); 
					b = (int)((hue*180)/3.14);
				
					System.out.println("x :"+x+"\t y :"+y+"\t red :"+r+"\t green :"+g+"\t blue"+b+"\t Intensity :"+intensity+"\t Saturation :"+ s+"\t Hue :"+hue);
				
				p = (a<<24) | (r<<16) | (g<<8) |  b;
				image.setRGB(x, y, p);
			}
		}
		
		try
		{
			f = new File("/Users/priteshratnappagol/Desktop/f_images/hue.png");
			ImageIO.write(image,"png", f);
			System.out.println("Writing Complete");
		}catch(IOException e){
			System.out.println("Error :"+e);
		}
	}//end of main 

}//end of class


