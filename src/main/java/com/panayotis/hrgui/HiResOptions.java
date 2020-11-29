package com.panayotis.hrgui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.DEFAULT_OPTION;

public class HiResOptions {
    private Component parent;
    private String title;
    private String message = "";
    private int type = JOptionPane.INFORMATION_MESSAGE;
    private String[] labels = {"OK"};

    private int selectedIndex = -1;

    public HiResOptions() {
    }

    public HiResOptions title(String title) {
        this.title = title;
        return this;
    }

    public HiResOptions message(String message) {
        this.message = message == null ? "" : message;
        return this;
    }

    public HiResOptions parent(Component parent) {
        this.parent = parent;
        return this;
    }

    public HiResOptions info() {
        type = JOptionPane.INFORMATION_MESSAGE;
        return this;
    }

    public HiResOptions warning() {
        type = JOptionPane.WARNING_MESSAGE;
        return this;
    }

    public HiResOptions error() {
        type = JOptionPane.ERROR_MESSAGE;
        return this;
    }

    public HiResOptions question() {
        type = JOptionPane.QUESTION_MESSAGE;
        return this;
    }

    public HiResOptions buttons(String... labels) {
        this.labels = labels;
        return this;
    }

    public int show() {
        setSelected(-1);
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        for (String line : message.split("\n", -1))
            messagePanel.add(new HiResLabel(line.isEmpty() ? " " : line));
        HiResButton[] buttons = new HiResButton[labels.length];
        for (int i = 0; i < labels.length; i++) {
            int current = i;
            HiResButton button = new HiResButton(labels[i]);
            button.setActionCommand(String.valueOf(i));
            button.addActionListener(e -> {
                setSelected(current);
                SwingUtilities.getWindowAncestor(button).setVisible(false);
            });
            buttons[i] = button;
        }
        buttons[buttons.length - 1].grabFocus();
        JOptionPane.showOptionDialog(
                parent,
                messagePanel,
                title,
                DEFAULT_OPTION,
                type,
                null,
                buttons,
                buttons[0]);
        return selectedIndex;
    }

    private void setSelected(int index) {
        this.selectedIndex = index;
    }
}
