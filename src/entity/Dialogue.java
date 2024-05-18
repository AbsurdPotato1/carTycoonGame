package entity;

import java.awt.Graphics2D;

public class Dialogue {
    String[] dialogue_seq = new String[10]; // Max 10 dialogues is the seq length
    public Dialogue(String[] arr) {
        dialogue_seq = arr;
    }

    public void runDialogue(Graphics2D g2)  {
        for(int i = 0 ; i < dialogue_seq.length && dialogue_seq[i] != null; i++) {
            g2.drawString(dialogue_seq[i], 500, 500);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
