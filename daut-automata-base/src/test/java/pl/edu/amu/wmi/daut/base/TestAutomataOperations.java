package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
* Testy różnych operacji na automatach.
*/
public class TestAutomataOperations extends TestCase {

    /**
* Test prostego automatu.
*/
    public final void testSimpleAutomaton() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q1);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
        State q10 = automatonB.addState();
        State q11 = automatonB.addState();
        State q12 = automatonB.addState();
        automatonB.addTransition(q10, q11, new CharTransitionLabel('a'));
        automatonB.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('a'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q10);
        automatonB.markAsFinal(q12);


        // proszę odkomentować, kiedy AutomataOperations.intersection
        // będzie gotowe!!!
        // AutomatonSpecification Result = AutomataOperations.intersection(automatonA, automatonB);
        // AutomatonByRecursion automaton = AutomatonByRecursion(Result);

        // assertTrue(automaton.accepts("aa"));
        // assertTrue(automaton.accepts("ab"));
        // assertFalse(automaton.accepts(""));
        // assertFalse(automaton.accepts("a"));

    }
    /** Test sprawdza metode Sum w AutomataOperations A i B. */
    public final void testSumAB() {
        /*Automat A */
            AutomatonSpecification automatonA = new NaiveAutomatonSpecification();
            State q0 = automatonA.addState();
            State q1 = automatonA.addState();
            automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
            automatonA.addLoop(q1, new CharTransitionLabel('a'));
            automatonA.addLoop(q1, new CharTransitionLabel('b'));
            automatonA.markAsInitial(q0);
            automatonA.markAsFinal(q1);
        /*Automat B*/
            AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
            State q0B = automatonB.addState();
            State q1B = automatonB.addState();
            State q2B = automatonB.addState();
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q0B);
            automatonB.markAsFinal(q2B);
            /* Test A z B ok */
            AutomatonSpecification result = AutomataOperations.sum(automatonA, automatonB);
            NondeterministicAutomatonByThompsonApproach automaton = new 
            NondeterministicAutomatonByThompsonApproach(result);
            assertTrue(automaton.accepts("aa"));
            assertTrue(automaton.accepts("ba"));
            assertTrue(automaton.accepts("aaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaa"));
            assertTrue(automaton.accepts("bb"));
            assertTrue(automaton.accepts("abbbbabbbabbb"));
            assertFalse(automaton.accepts("bbb"));
            assertFalse(automaton.accepts("tegomaniezakceptowac"));
            assertFalse(automaton.accepts("baaaaaaaaaa"));
            assertFalse(automaton.accepts("aaaaaaaaaaaaaaaxaaaaaa"));
            assertFalse(automaton.accepts("bab"));
    }    
    /** Test sprawdza metode Sum w AutomataOperations B i D */
            public final void testSumBD() {
                /*Automat B*/
            AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
            State q0B = automatonB.addState();
            State q1B = automatonB.addState();
            State q2B = automatonB.addState();
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q0B);
            automatonB.markAsFinal(q2B);            
                    /* Automat D */
            AutomatonSpecification automatonD = new NaiveAutomatonSpecification();
            State q0D = automatonD.addState();
            State q1D = automatonD.addState();
            State q2D = automatonD.addState();
            State q3D = automatonD.addState();
            automatonD.addTransition(q0D, q1D, new CharTransitionLabel('a'));
            automatonD.addTransition(q0D, q2D, new CharTransitionLabel('b'));
            automatonD.addTransition(q1D, q3D, new CharTransitionLabel('a'));
            automatonD.addTransition(q1D, q2D, new CharTransitionLabel('b'));
            automatonD.addTransition(q2D, q0D, new CharTransitionLabel('c'));
            automatonD.addTransition(q2D, q1D, new CharTransitionLabel('b'));
            automatonD.addTransition(q2D, q3D, new CharTransitionLabel('a'));
            automatonD.addTransition(q3D, q2D, new CharTransitionLabel('c'));
            automatonD.addTransition(q3D, q0D, new CharTransitionLabel('b'));
            automatonD.markAsInitial(q0D);
            automatonD.markAsFinal(q3D);            
                    /*Test D z B ok */
            AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonD);
            NondeterministicAutomatonByThompsonApproach automaton = new 
            NondeterministicAutomatonByThompsonApproach(result);
            assertTrue(automaton.accepts("ab"));
            assertTrue(automaton.accepts("abbabba"));
            assertTrue(automaton.accepts("bbbcaacba"));
            assertTrue(automaton.accepts("aacacaca"));
            assertTrue(automaton.accepts("aa"));
            assertFalse(automaton.accepts("zle"));
            assertFalse(automaton.accepts("b"));
            assertFalse(automaton.accepts(""));
            assertFalse(automaton.accepts("aac"));
    }   
            /** Test sprawdza metode Sum w AutomataOperations B i C. */
            public final void testSumBC() {
           /* Automat B*/
            AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
            State q0B = automatonB.addState();
            State q1B = automatonB.addState();
            State q2B = automatonB.addState();
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q0B);
            automatonB.markAsFinal(q2B);
        /*Automat C */
            AutomatonSpecification automatonC = new NaiveAutomatonSpecification();
            State q0C = automatonC.addState();
            automatonC.addLoop(q0C, new CharTransitionLabel('a'));
            automatonC.addLoop(q0C, new CharTransitionLabel('b'));
            automatonC.addLoop(q0C, new CharTransitionLabel('c'));
            automatonC.addLoop(q0C, new CharTransitionLabel('d'));
            automatonC.markAsInitial(q0C);
            automatonC.markAsFinal(q0C);            
                    /*Test B z C ok */
            AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonC);
            NondeterministicAutomatonByThompsonApproach automaton = new 
            NondeterministicAutomatonByThompsonApproach(result);
            assertTrue(automaton.accepts("babbaccddcaaccb"));
            assertTrue(automaton.accepts("bbaccddbaba"));
            assertTrue(automaton.accepts("bbbcaacba"));
            assertTrue(automaton.accepts("aaaaaaaaaaaaaaaa"));
            assertTrue(automaton.accepts(""));
            assertFalse(automaton.accepts("bbaccddxbaba"));
            assertFalse(automaton.accepts("czytwojprogrammackutozaakceptuje"));
            assertFalse(automaton.accepts("zielonosmutnaniebieskowesolapomaranczowa"));
       }
            /** Test sprawdza metode Sum w AutomataOperations B i E. */
                public final void testSumBE() {                    
                               /* Automat B*/
            AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
            State q0B = automatonB.addState();
            State q1B = automatonB.addState();
            State q2B = automatonB.addState();
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q0B);
            automatonB.markAsFinal(q2B);
            /*Automat E*/
            AutomatonSpecification automatonE = new NaiveAutomatonSpecification();
            State q0E = automatonE.addState();
            automatonE.addTransition(q0E, q0E, new EpsilonTransitionLabel());
            automatonE.markAsInitial(q0E);
            automatonE.markAsFinal(q0E);
            /*Test B z E */
            AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonE);
            NondeterministicAutomatonByThompsonApproach automaton = new 
            NondeterministicAutomatonByThompsonApproach(result);
            assertTrue(automaton.accepts(""));
            assertTrue(automaton.accepts("aa"));
            assertFalse(automaton.accepts("bbaccddxbaba"));
            assertFalse(automaton.accepts("aabbbaaaa"));
        }
                /** Test sprawdza metode Sum w AutomataOperations B i F */
            public final void testSumBF() {
                /*Automat B*/
            AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
            State q0B = automatonB.addState();
            State q1B = automatonB.addState();
            State q2B = automatonB.addState();
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q0B);
            automatonB.markAsFinal(q2B);
            /*Automat F */
            AutomatonSpecification automatonF = new NaiveAutomatonSpecification();
            State q0F = automatonF.addState();
            State q1F = automatonF.addState();
            State q2F = automatonF.addState();
            State q3F = automatonF.addState();
            State q7F = automatonF.addState();
            State q5F = automatonF.addState();
            State q6F = automatonF.addState();
            automatonF.addTransition(q0F, q1F, new CharTransitionLabel('a'));
            automatonF.addTransition(q0F, q3F, new EpsilonTransitionLabel());
            automatonF.addTransition(q0F, q2F, new EpsilonTransitionLabel());
            automatonF.addTransition(q3F, q7F, new CharTransitionLabel('a'));
            automatonF.addTransition(q2F, q5F, new EpsilonTransitionLabel());
            automatonF.addTransition(q5F, q6F, new CharTransitionLabel('b'));
            /*Test Automatu B i F */           
            AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonF);
            NondeterministicAutomatonByThompsonApproach automaton = new 
            NondeterministicAutomatonByThompsonApproach(result);
            assertTrue(automaton.accepts("aa"));
            assertTrue(automaton.accepts("b"));
            assertTrue(automaton.accepts("a"));
            assertFalse(automaton.accepts("aaabbbb"));
            assertFalse(automaton.accepts(""));
            }
}

