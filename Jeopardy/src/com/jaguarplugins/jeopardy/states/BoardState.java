package com.jaguarplugins.jeopardy.states;

import com.jaguarplugins.jeopardy.questions.Question;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class BoardState extends State {

	private final int MULTIPLIER = 10, SMOOTH = 80;
	
	public BoardState(GraphicsContext g, Handler handler) {
		super(g, handler);
	}

	@Override
	public void render() {
		
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("calibri", handler.getGridWidth()/5));
		
		for (int category = 0; category <= 4; category++) {
			
			g.setFill(Color.ROYALBLUE);
			g.fillRoundRect(handler.getHGrid(category) + 1, handler.getVGrid(0) + 1, handler.getGridWidth() - 2, handler.getGridHeight() - 2, 
					handler.getHeight()/SMOOTH, handler.getHeight()/SMOOTH);
		
			g.setFill(Color.BLACK);
			g.fillText(handler.getQuestions()[category][0].getCategory(), handler.getHGrid(category) + handler.getGridWidth()/2, handler.getVGrid(0) + handler.getGridHeight() / 2, handler.getGridWidth()*0.9);
			
		}
		
		for (Question[] category : handler.getQuestions()) {

			for(Question q : category) {

				if (q.isHover()) {
					g.setFill(Color.DEEPSKYBLUE);
				} else {
					g.setFill(Color.CORNFLOWERBLUE);
				}
				g.fillRoundRect(handler.getHGrid(q.getX()) + 1, handler.getVGrid(q.getY()) + 1, handler.getGridWidth() - 2, handler.getGridHeight() - 2, 
						handler.getHeight()/SMOOTH, handler.getHeight()/SMOOTH);
				
//				TEXT
				if (!q.isAnswered()) {
					
					g.setFill(Color.BLACK);
					int mny = (int) ((q.getY()) * MULTIPLIER);
					g.fillText("" + mny, handler.getHGrid(q.getX()) + handler.getGridWidth()/2, handler.getVGrid(q.getY()) + handler.getGridHeight() / 2);
					
				}
			
			}

		}
		
		g.setFont(new Font("calibri", handler.getGridWidth()/8));
		g.setFill(Color.BLACK);
		g.fillText(handler.getCurrentTeam().getName(), handler.getWidth()/2, handler.getVGrid(6) + handler.getGridHeight()/4);
		
	}

}