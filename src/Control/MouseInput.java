package Control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	private Handler handler;

	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		if(mx>=100 && mx <= 600){
			if(my>=100 && my <=600){
				
				State.setState(handler.getGame().gameState);
				
			}
		}
		if(mx>=600 && mx <= 1100){
			if(my>=100 && my <=600){
				
				State.setState(handler.getGame().gameState);
				
			}
		}
		if(mx>=1100 && mx <= 1700){
			if(my>=100 && my <=600){
				State.setState(handler.getGame().gameState);
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

