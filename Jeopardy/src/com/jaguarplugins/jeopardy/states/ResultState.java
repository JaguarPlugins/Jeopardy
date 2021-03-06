package com.jaguarplugins.jeopardy.states;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.jaguarplugins.jeopardy.questions.Team;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ResultState extends State {

	private static int stage = -1;
	private List<Team> orderedTeams;
	
	public ResultState(GraphicsContext g, Handler handler) {
		super(g, handler);
		stage = -1;
		orderedTeams = Arrays.asList(handler.getTeams());
		Collections.sort(orderedTeams);
	}

	@Override
	public void render() {

		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("calibri", handler.getGridWidth()/5));
		g.setFill(Color.BLACK);
		
		if (stage >= 0) {
		
			double height = (handler.getHeight() - handler.getHeight()/6)/handler.teamsLength();
			
			for (int x = 0; x < orderedTeams.size(); x++) {
				if (x <= stage) {
					g.fillText(orderedTeams.get(x).getName() + ": " + orderedTeams.get(x).getScore(), 
							handler.getWidth()/2, handler.getHeight()/12 + x*height);
				}
			}
		
		}
		
//		Controls help
		double bWidth = handler.getGridWidth()/8;
		double y = handler.getVGrid(6) + (handler.getHeight() - handler.getVGrid(6))/2;
		double x = handler.getGridWidth()/6;
		
		g.setFill(Color.DIMGRAY);
		g.setStroke(Color.DIMGRAY);
		g.setFont(new Font("calibri", handler.getGridWidth()/12));
		g.strokeRoundRect(handler.getHGrid(0) + x - bWidth/2, y-bWidth/2, bWidth, bWidth, handler.getGridHeight()/10, handler.getGridHeight()/10);
		g.fillText("Esc", handler.getHGrid(0) + x, y);
		
		g.setFont(new Font("calibri", handler.getGridWidth()/16));
		g.setTextAlign(TextAlignment.LEFT);
		g.fillText("Back to questions", handler.getHGrid(0) + 1.5*x, y);
		
	}
	
	public static void nextStage(Handler handler) {
		stage++;;
		handler.getCurrentState().render();
	}

}
