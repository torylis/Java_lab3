import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();
    private String needle = null;
    private boolean find = false;
    private final DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    public GornerTableCellRenderer() {
        // Показывать только 10 знаков после запятой
        formatter.setMaximumFractionDigits(10);
        // Не использовать группировку (т.е. не отделять тысячи)
        formatter.setGroupingUsed(false);
        // Установить точку в качестве разделителя дробной части
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);

        // Разместить надпись внутри панели
        panel.add(label);
        // Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String formattedDouble = formatter.format(value);

        if (col == 1 && needle != null && needle.equals(formattedDouble)) {
            label.setText("");
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(true);
            panel.add(checkBox);
        } else {
            for (Component comp : panel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    panel.remove(comp);
                }
            }
            label.setText(formattedDouble);
        }
        if (find & isPalindrome(formattedDouble)) {
            panel.setBackground(Color.PINK);
        }
        else
            panel.setBackground(Color.WHITE);
        return panel;
    }

    public boolean isPalindrome(String data) {
        String str = data.replace(".", "");
        StringBuilder sb = new StringBuilder(str);
        return str.contentEquals(sb.reverse());
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }

    public void setFind() {
        this.find = true;
    }
}
