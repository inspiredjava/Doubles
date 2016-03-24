/**
 * Created by inspiredjava on 3/23/16.
 */
public class Doubles {

    public Double parse(String s) {
        StateMachine automate = new StateMachine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            automate.next(c);
        }
        return automate.getResult();
    }

    private static class StateMachine {
        State currentState  = State.INIT;
        ParseData parseData = new ParseData();

        public void next(char c) {
            currentState = currentState.next(c, parseData);
        }

        public Double getResult() {
            if (currentState == State.NUMBER) return new Double(parseData.getNumber());
            return null;
        }

        public enum State {
            INIT {
                @Override
                public State next(char c, ParseData parseData) {
                    if (c == '-' || c == '+') {
                        parseData.setSign(c);
                        return NUMBER;
                    }

                    int digit = c - '0';
                    if (digit <= 9 && digit >= 0) {
                        parseData.addDigit(digit);
                        return NUMBER;
                    }
                    return INVALID_END;
                }
            }, NUMBER {
                @Override
                public State next(char c, ParseData parseData) {
                    int digit = c - '0';
                    if (digit <= 9 && digit >= 0) {
                        parseData.addDigit(digit);
                        return NUMBER;
                    }
                    return INVALID_END;
                }
            }, VALID_END {
                @Override
                public State next(char c, ParseData parseData) {
                    if (c == ' ') return VALID_END;
                    return INVALID_END;
                }
            }, INVALID_END {
                @Override
                public State next(char c, ParseData parseData) {
                    return INVALID_END;
                }
            };

            public abstract State next(char c, ParseData parseData);
        }

        public static class ParseData {
            private int number;
            private int sign = 1;

            public void addDigit(int digit) {
                number = number * 10 + digit;
            }

            public int getNumber() {
                return number * sign;
            }

            public void setSign(char sign) {
                if (sign == '-') {
                    this.sign = -1;
                }
            }
        }
    }
}
