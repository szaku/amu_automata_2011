package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomataOperations;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

/**
 * Klasa reprezentująca niejawny, dwuargumentowy operator konkatenacji.
 */
public class ConcatenationOperator extends BinaryRegexpOperator {

    /**
     * Konstruktor domyslny.
     */
    public ConcatenationOperator() { }

    @Override
    public final AutomatonSpecification createAutomatonFromTwoAutomata(
            AutomatonSpecification leftSubautomaton,
            AutomatonSpecification rightSubautomaton) {
        return AutomataOperations.concatenation(leftSubautomaton, rightSubautomaton);
    }
}
