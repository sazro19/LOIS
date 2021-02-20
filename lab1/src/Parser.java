public class Parser {

    private StringBuilder formula;
    private int index;

    Parser(StringBuilder formula) {
        this.formula = formula;
        System.out.println(isFormula());
    }

    private boolean isFormula() {
        for (index = 0; index < formula.length(); index++) {
            if (formula.charAt(index) == '(') {
                if (formula.charAt(index + 1) != '!' || !isAtom(formula.charAt(index + 2)) ||
                        formula.charAt(index + 3) != ')') {
                    if (!isSimpleDisjunction(index + 1)) {
                        return false;
                    }
                } else {
                    index += 3;
                }
            }
            if (formula.charAt(index) == ')') {
                if (index != formula.length() - 1) {
                    if (formula.charAt(index + 1) != '/') {
                        return false;
                    }
                }
            }
            if (formula.charAt(index) == '/') {
                if (formula.charAt(index + 1) != '\\') {
                    return false;
                }
            }
            if (formula.charAt(index) == '\\') {
                if (formula.charAt(index + 1) != '(' && !isAtom(formula.charAt(index + 1))) {
                    return false;
                }
            }
            if (isAtom(formula.charAt(index))) {
                if (index != formula.length() - 1) {
                    if (formula.charAt(index + 1) != '/') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isSimpleDisjunction(int index) {
        if (!isAtom(formula.charAt(index)) || formula.charAt(index + 1) == ')') {
            if (formula.charAt(index) != '(') {
                return false;
            }
        }
        for (int i = index; ; i++) {
            if (formula.charAt(i) == '(') {
                if (formula.charAt(i + 1) != '!' || !isAtom(formula.charAt(i + 2)) ||
                        formula.charAt(i + 3) != ')') {
                    return false;
                } else {
                    i += 4;
                    if (formula.charAt(i) == ')') {
                        this.index = i;
                        return true;
                    }
                }
            }
            if (formula.charAt(i) == '\\') {
                if (formula.charAt(i + 1) != '/') {
                    return false;
                }
            }
            if (formula.charAt(i) == '/') {
                if (!isAtom(formula.charAt(i + 1)) && formula.charAt(i + 1) != '(') {
                    return false;
                }
            }
            if (isAtom(formula.charAt(i))) {
                if (formula.charAt(i + 1) == ')') {
                    this.index = i + 1;
                    return true;
                }
                if (formula.charAt(i + 1) != '\\') {
                    return false;
                }
            }
        }
    }

    private boolean isAtom(char c) {
        return (int) c >= 65 && (int) c <= 90;
    }
}

