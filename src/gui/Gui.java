package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	final static int fWidth = 500, fHeight = 500;
	final static int width = 5, height = 5; // 퍼즐크기 정사각형만 가능
	int cIndex = width * height - 1; // 총 인덱스
	JPanel jp1;
	JLabel jlb1[];
	JLabel jlbtemp; // 스왑용 라벨
	int inputKey; // 상하좌우
	Point cPoint; // 빈칸의 좌표
	int rNum1; // 1~100 난수
	int rNum2; // 37~40 난수

	public Gui() {
		super("퍼즐게임");
		jp1 = new JPanel();
		jlb1 = new JLabel[width * height];
		jlbtemp = new JLabel();
		cPoint = new Point();

		/* 라벨 생성 */
		for (int i = 0; i < width * height; i++) {
			if (i == (width * height - 1)) {
				jlb1[i] = new JLabel("무빙");
				jlb1[i].setFont(new Font("Serif", Font.BOLD, 30));
				jlb1[i].setHorizontalAlignment(JLabel.CENTER);
				jlb1[i].setEnabled(false);
				jp1.add(jlb1[i]);
				continue;
			}
			jlb1[i] = new JLabel(String.valueOf(i + 1));
			jlb1[i].setFont(new Font("Serif", Font.BOLD, 30));
			jlb1[i].setHorizontalAlignment(JLabel.CENTER);
			jlb1[i].setEnabled(false);
			jp1.add(jlb1[i]);
		}
		jp1.setLayout(new GridLayout(width, height));

		/* 키보드 액션리스너 */
		addKeyListener(new KeyListener() {
			/* 엔터키면 랜덤 방향키로 섞기, 그 외에는 방향키로 play 호출 */
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (e.getKeyCode() == 10) {
						rNum1 = (int) (Math.random() * 100) + 1;
						for (int i = 0; i < rNum1; i++) {
							inputKey = (int) (Math.random() * 4) + 37;
							play(inputKey);
						}
					}
				} else
					play(e.getKeyCode());
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		add(jp1);
		setSize(fWidth, fHeight);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void play(int keyCode) {
		cPoint.setLocation(jlb1[cIndex].getLocation()); // 무빙의 현재좌표
		inputKey = keyCode;
		switch (inputKey) {
		case 38:
			if (cIndex < width) {
				System.out.println("배열을 초과");
				break;
			}
			System.out.println("위");
			jlbtemp.setText(jlb1[cIndex - width].getText());
			jlb1[cIndex - width].setText(jlb1[cIndex].getText());
			jlb1[cIndex].setText(jlbtemp.getText());
			cIndex = cIndex - width;
			break;
		case 39:
			if (cIndex % width == width - 1) {
				System.out.println("배열을 초과");
				break;
			}
			System.out.println("오른쪽");
			jlbtemp.setText(jlb1[cIndex + 1].getText());
			jlb1[cIndex + 1].setText(jlb1[cIndex].getText());
			jlb1[cIndex].setText(jlbtemp.getText());
			cIndex++;
			break;
		case 37:
			if (cIndex % width == 0) {
				System.out.println("배열을 초과");
				break;
			}
			System.out.println("왼쪽");
			jlbtemp.setText(jlb1[cIndex - 1].getText());
			jlb1[cIndex - 1].setText(jlb1[cIndex].getText());
			jlb1[cIndex].setText(jlbtemp.getText());
			cIndex--;
			break;
		case 40:
			if (cIndex >= width * height - width) {
				System.out.println("배열을 초과");
				break;
			}
			System.out.println("아래");
			jlbtemp.setText(jlb1[cIndex + width].getText());
			jlb1[cIndex + width].setText(jlb1[cIndex].getText());
			jlb1[cIndex].setText(jlbtemp.getText());
			cIndex = cIndex + width;
			break;
		default:
			System.out.println("잘못된 접근");
			break;
		}
	}

	public static void main(String[] args) {
		new Gui();
	}
}
