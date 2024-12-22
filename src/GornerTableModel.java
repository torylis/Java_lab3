import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    private final Double[] coefficients;
    private final Double from;
    private final Double to;
    private final Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
// В данной модели два столбца
        return 4;
    }

    public int getRowCount() {
        return (int) Math.ceil((to - from) / step) + 1;
    }

    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        Double x = from + step * row;
        if (col == 0) {
// Если запрашивается значение 1-го столбца, то это X
            return x;
        } else  if (col==1) {
// Если запрашивается значение 2-го столбца, то это значение
// многочлена
            Double result = 0.0;
            for (Double coefficient : coefficients) {
                result = coefficient + result * x;
            }
            return result;
        } else  if (col==2) {
            Float result = 0.0F;
            for (double coefficient : coefficients) {
                result = (float) ((float)coefficient + result * x);
            }
            return result;
        } else {
            Double result2 = 0.0;
            for (Double coefficient : coefficients) {
                result2 = coefficient + result2 * x;
            }
            Float result3 = 0.0F;
            for (double coefficient : coefficients) {
                result3 = (float) ((float)coefficient + result3 * x);
            }
            return Math.abs(result2 - (Float) result3);
        }

    }
    public String getColumnName(int col) {
        if (col == 0) {// Название 1-го столбца
            return "Значение X";
        } else  if (col==1) {
            return "Значение многочлена double";
        } else  if (col==2) {
            return "Значение многочлена float";
        }
        return "Разница между 2 и 3 столбцом";
    }
    public Class<?> getColumnClass(int col) {
        return Double.class;
    }
}