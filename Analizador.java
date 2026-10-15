
import java.util.*;

public class Analizador {
    // ACTION[estado][terminal] = accion
    private final Map<Integer, Map<String, String>> ACTION = new HashMap<>();

    // GOTO[estado][noTerminal] = estado siguiente
    private final Map<Integer, Map<String, Integer>> GOTO = new HashMap<>();

    // Producciones numeradas de la gramatica
    private final Map<Integer, Produccion> producciones = new HashMap<>();

    private static class Produccion {
        String izquierda;
        int longitudDerecha;

        public Produccion(String izquierda, int longitudDerecha) {
            this.izquierda = izquierda;
            this.longitudDerecha = longitudDerecha;
        }
    }

    public Analizador(){
        // ESTADO 0
        Map<String, String> action0 = new HashMap<>();
        Map<String, Integer> goto0 = new HashMap<>();
        action0.put("(", "s17");
        action0.put("bool", "s16");
        action0.put("id", "s14");
        action0.put("if", "s6");
        action0.put("let", "s4");
        action0.put("num", "s15");
        goto0.put("Add", 10);
        goto0.put("App", 12);
        goto0.put("Atom", 13);
        goto0.put("Comp", 9);
        goto0.put("Decl", 3);
        goto0.put("Decls", 2);
        goto0.put("Expr", 5);
        goto0.put("Factor", 11);
        goto0.put("Logic", 8);
        goto0.put("Pipe", 7);
        goto0.put("Program", 1);
        ACTION.put(0, action0);
        GOTO.put(0, goto0);

        // ESTADO 1
        Map<String, String> action1 = new HashMap<>();
        Map<String, Integer> goto1 = new HashMap<>();
        action1.put("$", "ACC");
        ACTION.put(1, action1);
        GOTO.put(1, goto1);

        // ESTADO 2
        Map<String, String> action2 = new HashMap<>();
        Map<String, Integer> goto2 = new HashMap<>();
        action2.put("(", "s17");
        action2.put("bool", "s16");
        action2.put("id", "s14");
        action2.put("if", "s6");
        action2.put("let", "s4");
        action2.put("num", "s15");
        action2.put("$", "r1");
        goto2.put("Add", 10);
        goto2.put("App", 12);
        goto2.put("Atom", 13);
        goto2.put("Comp", 9);
        goto2.put("Decl", 18);
        goto2.put("Expr", 5);
        goto2.put("Factor", 11);
        goto2.put("Logic", 8);
        goto2.put("Pipe", 7);
        ACTION.put(2, action2);
        GOTO.put(2, goto2);

        // ESTADO 3
        Map<String, String> action3 = new HashMap<>();
        Map<String, Integer> goto3 = new HashMap<>();
        action3.put("(", "r3");
        action3.put("bool", "r3");
        action3.put("id", "r3");
        action3.put("if", "r3");
        action3.put("let", "r3");
        action3.put("num", "r3");
        action3.put("$", "r3");
        ACTION.put(3, action3);
        GOTO.put(3, goto3);

        // ESTADO 4
        Map<String, String> action4 = new HashMap<>();
        Map<String, Integer> goto4 = new HashMap<>();
        action4.put("id", "s19");
        action4.put("rec", "s20");
        ACTION.put(4, action4);
        GOTO.put(4, goto4);

        // ESTADO 5
        Map<String, String> action5 = new HashMap<>();
        Map<String, Integer> goto5 = new HashMap<>();
        action5.put(";;", "s21");
        ACTION.put(5, action5);
        GOTO.put(5, goto5);

        // ESTADO 6
        Map<String, String> action6 = new HashMap<>();
        Map<String, Integer> goto6 = new HashMap<>();
        action6.put("(", "s17");
        action6.put("bool", "s16");
        action6.put("id", "s14");
        action6.put("if", "s6");
        action6.put("let", "s22");
        action6.put("num", "s15");
        goto6.put("Add", 10);
        goto6.put("App", 12);
        goto6.put("Atom", 13);
        goto6.put("Comp", 9);
        goto6.put("Expr", 23);
        goto6.put("Factor", 11);
        goto6.put("Logic", 8);
        goto6.put("Pipe", 7);
        ACTION.put(6, action6);
        GOTO.put(6, goto6);

        // ESTADO 7
        Map<String, String> action7 = new HashMap<>();
        Map<String, Integer> goto7 = new HashMap<>();
        action7.put(")", "r11");
        action7.put(";;", "r11");
        action7.put("else", "r11");
        action7.put("in", "r11");
        action7.put("then", "r11");
        action7.put("|>", "s24");
        ACTION.put(7, action7);
        GOTO.put(7, goto7);

        // ESTADO 8
        Map<String, String> action8 = new HashMap<>();
        Map<String, Integer> goto8 = new HashMap<>();
        action8.put("&&", "s25");
        action8.put(")", "r13");
        action8.put(";;", "r13");
        action8.put("else", "r13");
        action8.put("in", "r13");
        action8.put("then", "r13");
        action8.put("|>", "r13");
        action8.put("||", "s26");
        ACTION.put(8, action8);
        GOTO.put(8, goto8);

        // ESTADO 9
        Map<String, String> action9 = new HashMap<>();
        Map<String, Integer> goto9 = new HashMap<>();
        action9.put("&&", "r16");
        action9.put(")", "r16");
        action9.put(";;", "r16");
        action9.put("else", "r16");
        action9.put("in", "r16");
        action9.put("then", "r16");
        action9.put("|>", "r16");
        action9.put("||", "r16");
        ACTION.put(9, action9);
        GOTO.put(9, goto9);

        // ESTADO 10
        Map<String, String> action10 = new HashMap<>();
        Map<String, Integer> goto10 = new HashMap<>();
        action10.put("&&", "r19");
        action10.put(")", "r19");
        action10.put("+", "s29");
        action10.put("-", "s30");
        action10.put(";;", "r19");
        action10.put("<", "s28");
        action10.put("==", "s27");
        action10.put("else", "r19");
        action10.put("in", "r19");
        action10.put("then", "r19");
        action10.put("|>", "r19");
        action10.put("||", "r19");
        ACTION.put(10, action10);
        GOTO.put(10, goto10);

        // ESTADO 11
        Map<String, String> action11 = new HashMap<>();
        Map<String, Integer> goto11 = new HashMap<>();
        action11.put("&&", "r22");
        action11.put(")", "r22");
        action11.put("*", "s31");
        action11.put("+", "r22");
        action11.put("-", "r22");
        action11.put(";;", "r22");
        action11.put("<", "r22");
        action11.put("==", "r22");
        action11.put("else", "r22");
        action11.put("in", "r22");
        action11.put("then", "r22");
        action11.put("|>", "r22");
        action11.put("||", "r22");
        ACTION.put(11, action11);
        GOTO.put(11, goto11);

        // ESTADO 12
        Map<String, String> action12 = new HashMap<>();
        Map<String, Integer> goto12 = new HashMap<>();
        action12.put("&&", "r24");
        action12.put("(", "s17");
        action12.put(")", "r24");
        action12.put("*", "r24");
        action12.put("+", "r24");
        action12.put("-", "r24");
        action12.put(";;", "r24");
        action12.put("<", "r24");
        action12.put("==", "r24");
        action12.put("bool", "s16");
        action12.put("else", "r24");
        action12.put("id", "s14");
        action12.put("in", "r24");
        action12.put("num", "s15");
        action12.put("then", "r24");
        action12.put("|>", "r24");
        action12.put("||", "r24");
        goto12.put("Atom", 32);
        ACTION.put(12, action12);
        GOTO.put(12, goto12);

        // ESTADO 13
        Map<String, String> action13 = new HashMap<>();
        Map<String, Integer> goto13 = new HashMap<>();
        action13.put("&&", "r26");
        action13.put("(", "r26");
        action13.put(")", "r26");
        action13.put("*", "r26");
        action13.put("+", "r26");
        action13.put("-", "r26");
        action13.put(";;", "r26");
        action13.put("<", "r26");
        action13.put("==", "r26");
        action13.put("bool", "r26");
        action13.put("else", "r26");
        action13.put("id", "r26");
        action13.put("in", "r26");
        action13.put("num", "r26");
        action13.put("then", "r26");
        action13.put("|>", "r26");
        action13.put("||", "r26");
        ACTION.put(13, action13);
        GOTO.put(13, goto13);

        // ESTADO 14
        Map<String, String> action14 = new HashMap<>();
        Map<String, Integer> goto14 = new HashMap<>();
        action14.put("&&", "r27");
        action14.put("(", "r27");
        action14.put(")", "r27");
        action14.put("*", "r27");
        action14.put("+", "r27");
        action14.put("-", "r27");
        action14.put(";;", "r27");
        action14.put("<", "r27");
        action14.put("==", "r27");
        action14.put("bool", "r27");
        action14.put("else", "r27");
        action14.put("id", "r27");
        action14.put("in", "r27");
        action14.put("num", "r27");
        action14.put("then", "r27");
        action14.put("|>", "r27");
        action14.put("||", "r27");
        ACTION.put(14, action14);
        GOTO.put(14, goto14);

        // ESTADO 15
        Map<String, String> action15 = new HashMap<>();
        Map<String, Integer> goto15 = new HashMap<>();
        action15.put("&&", "r28");
        action15.put("(", "r28");
        action15.put(")", "r28");
        action15.put("*", "r28");
        action15.put("+", "r28");
        action15.put("-", "r28");
        action15.put(";;", "r28");
        action15.put("<", "r28");
        action15.put("==", "r28");
        action15.put("bool", "r28");
        action15.put("else", "r28");
        action15.put("id", "r28");
        action15.put("in", "r28");
        action15.put("num", "r28");
        action15.put("then", "r28");
        action15.put("|>", "r28");
        action15.put("||", "r28");
        ACTION.put(15, action15);
        GOTO.put(15, goto15);

        // ESTADO 16
        Map<String, String> action16 = new HashMap<>();
        Map<String, Integer> goto16 = new HashMap<>();
        action16.put("&&", "r29");
        action16.put("(", "r29");
        action16.put(")", "r29");
        action16.put("*", "r29");
        action16.put("+", "r29");
        action16.put("-", "r29");
        action16.put(";;", "r29");
        action16.put("<", "r29");
        action16.put("==", "r29");
        action16.put("bool", "r29");
        action16.put("else", "r29");
        action16.put("id", "r29");
        action16.put("in", "r29");
        action16.put("num", "r29");
        action16.put("then", "r29");
        action16.put("|>", "r29");
        action16.put("||", "r29");
        ACTION.put(16, action16);
        GOTO.put(16, goto16);

        // ESTADO 17
        Map<String, String> action17 = new HashMap<>();
        Map<String, Integer> goto17 = new HashMap<>();
        action17.put("(", "s17");
        action17.put("bool", "s16");
        action17.put("id", "s14");
        action17.put("if", "s6");
        action17.put("let", "s22");
        action17.put("num", "s15");
        goto17.put("Add", 10);
        goto17.put("App", 12);
        goto17.put("Atom", 13);
        goto17.put("Comp", 9);
        goto17.put("Expr", 33);
        goto17.put("Factor", 11);
        goto17.put("Logic", 8);
        goto17.put("Pipe", 7);
        ACTION.put(17, action17);
        GOTO.put(17, goto17);

        // ESTADO 18
        Map<String, String> action18 = new HashMap<>();
        Map<String, Integer> goto18 = new HashMap<>();
        action18.put("(", "r2");
        action18.put("bool", "r2");
        action18.put("id", "r2");
        action18.put("if", "r2");
        action18.put("let", "r2");
        action18.put("num", "r2");
        action18.put("$", "r2");
        ACTION.put(18, action18);
        GOTO.put(18, goto18);

        // ESTADO 19
        Map<String, String> action19 = new HashMap<>();
        Map<String, Integer> goto19 = new HashMap<>();
        action19.put("=", "s36");
        action19.put("id", "s35");
        goto19.put("Params", 34);
        ACTION.put(19, action19);
        GOTO.put(19, goto19);

        // ESTADO 20
        Map<String, String> action20 = new HashMap<>();
        Map<String, Integer> goto20 = new HashMap<>();
        action20.put("id", "s37");
        ACTION.put(20, action20);
        GOTO.put(20, goto20);

        // ESTADO 21
        Map<String, String> action21 = new HashMap<>();
        Map<String, Integer> goto21 = new HashMap<>();
        action21.put("(", "r6");
        action21.put("bool", "r6");
        action21.put("id", "r6");
        action21.put("if", "r6");
        action21.put("let", "r6");
        action21.put("num", "r6");
        action21.put("$", "r6");
        ACTION.put(21, action21);
        GOTO.put(21, goto21);

        // ESTADO 22
        Map<String, String> action22 = new HashMap<>();
        Map<String, Integer> goto22 = new HashMap<>();
        action22.put("id", "s38");
        ACTION.put(22, action22);
        GOTO.put(22, goto22);

        // ESTADO 23
        Map<String, String> action23 = new HashMap<>();
        Map<String, Integer> goto23 = new HashMap<>();
        action23.put("then", "s39");
        ACTION.put(23, action23);
        GOTO.put(23, goto23);

        // ESTADO 24
        Map<String, String> action24 = new HashMap<>();
        Map<String, Integer> goto24 = new HashMap<>();
        action24.put("(", "s17");
        action24.put("bool", "s16");
        action24.put("id", "s14");
        action24.put("num", "s15");
        goto24.put("Add", 10);
        goto24.put("App", 12);
        goto24.put("Atom", 13);
        goto24.put("Comp", 9);
        goto24.put("Factor", 11);
        goto24.put("Logic", 40);
        ACTION.put(24, action24);
        GOTO.put(24, goto24);

        // ESTADO 25
        Map<String, String> action25 = new HashMap<>();
        Map<String, Integer> goto25 = new HashMap<>();
        action25.put("(", "s17");
        action25.put("bool", "s16");
        action25.put("id", "s14");
        action25.put("num", "s15");
        goto25.put("Add", 10);
        goto25.put("App", 12);
        goto25.put("Atom", 13);
        goto25.put("Comp", 41);
        goto25.put("Factor", 11);
        ACTION.put(25, action25);
        GOTO.put(25, goto25);

        // ESTADO 26
        Map<String, String> action26 = new HashMap<>();
        Map<String, Integer> goto26 = new HashMap<>();
        action26.put("(", "s17");
        action26.put("bool", "s16");
        action26.put("id", "s14");
        action26.put("num", "s15");
        goto26.put("Add", 10);
        goto26.put("App", 12);
        goto26.put("Atom", 13);
        goto26.put("Comp", 42);
        goto26.put("Factor", 11);
        ACTION.put(26, action26);
        GOTO.put(26, goto26);

        // ESTADO 27
        Map<String, String> action27 = new HashMap<>();
        Map<String, Integer> goto27 = new HashMap<>();
        action27.put("(", "s17");
        action27.put("bool", "s16");
        action27.put("id", "s14");
        action27.put("num", "s15");
        goto27.put("Add", 43);
        goto27.put("App", 12);
        goto27.put("Atom", 13);
        goto27.put("Factor", 11);
        ACTION.put(27, action27);
        GOTO.put(27, goto27);

        // ESTADO 28
        Map<String, String> action28 = new HashMap<>();
        Map<String, Integer> goto28 = new HashMap<>();
        action28.put("(", "s17");
        action28.put("bool", "s16");
        action28.put("id", "s14");
        action28.put("num", "s15");
        goto28.put("Add", 44);
        goto28.put("App", 12);
        goto28.put("Atom", 13);
        goto28.put("Factor", 11);
        ACTION.put(28, action28);
        GOTO.put(28, goto28);

        // ESTADO 29
        Map<String, String> action29 = new HashMap<>();
        Map<String, Integer> goto29 = new HashMap<>();
        action29.put("(", "s17");
        action29.put("bool", "s16");
        action29.put("id", "s14");
        action29.put("num", "s15");
        goto29.put("App", 12);
        goto29.put("Atom", 13);
        goto29.put("Factor", 45);
        ACTION.put(29, action29);
        GOTO.put(29, goto29);

        // ESTADO 30
        Map<String, String> action30 = new HashMap<>();
        Map<String, Integer> goto30 = new HashMap<>();
        action30.put("(", "s17");
        action30.put("bool", "s16");
        action30.put("id", "s14");
        action30.put("num", "s15");
        goto30.put("App", 12);
        goto30.put("Atom", 13);
        goto30.put("Factor", 46);
        ACTION.put(30, action30);
        GOTO.put(30, goto30);

        // ESTADO 31
        Map<String, String> action31 = new HashMap<>();
        Map<String, Integer> goto31 = new HashMap<>();
        action31.put("(", "s17");
        action31.put("bool", "s16");
        action31.put("id", "s14");
        action31.put("num", "s15");
        goto31.put("App", 47);
        goto31.put("Atom", 13);
        ACTION.put(31, action31);
        GOTO.put(31, goto31);

        // ESTADO 32
        Map<String, String> action32 = new HashMap<>();
        Map<String, Integer> goto32 = new HashMap<>();
        action32.put("&&", "r25");
        action32.put("(", "r25");
        action32.put(")", "r25");
        action32.put("*", "r25");
        action32.put("+", "r25");
        action32.put("-", "r25");
        action32.put(";;", "r25");
        action32.put("<", "r25");
        action32.put("==", "r25");
        action32.put("bool", "r25");
        action32.put("else", "r25");
        action32.put("id", "r25");
        action32.put("in", "r25");
        action32.put("num", "r25");
        action32.put("then", "r25");
        action32.put("|>", "r25");
        action32.put("||", "r25");
        ACTION.put(32, action32);
        GOTO.put(32, goto32);

        // ESTADO 33
        Map<String, String> action33 = new HashMap<>();
        Map<String, Integer> goto33 = new HashMap<>();
        action33.put(")", "s48");
        ACTION.put(33, action33);
        GOTO.put(33, goto33);

        // ESTADO 34
        Map<String, String> action34 = new HashMap<>();
        Map<String, Integer> goto34 = new HashMap<>();
        action34.put("=", "s49");
        action34.put("id", "s50");
        ACTION.put(34, action34);
        GOTO.put(34, goto34);

        // ESTADO 35
        Map<String, String> action35 = new HashMap<>();
        Map<String, Integer> goto35 = new HashMap<>();
        action35.put("=", "r8");
        action35.put("id", "r8");
        ACTION.put(35, action35);
        GOTO.put(35, goto35);

        // ESTADO 36
        Map<String, String> action36 = new HashMap<>();
        Map<String, Integer> goto36 = new HashMap<>();
        action36.put("(", "s17");
        action36.put("bool", "s16");
        action36.put("id", "s14");
        action36.put("if", "s6");
        action36.put("let", "s22");
        action36.put("num", "s15");
        goto36.put("Add", 10);
        goto36.put("App", 12);
        goto36.put("Atom", 13);
        goto36.put("Comp", 9);
        goto36.put("Expr", 51);
        goto36.put("Factor", 11);
        goto36.put("Logic", 8);
        goto36.put("Pipe", 7);
        ACTION.put(36, action36);
        GOTO.put(36, goto36);

        // ESTADO 37
        Map<String, String> action37 = new HashMap<>();
        Map<String, Integer> goto37 = new HashMap<>();
        action37.put("id", "s35");
        goto37.put("Params", 52);
        ACTION.put(37, action37);
        GOTO.put(37, goto37);

        // ESTADO 38
        Map<String, String> action38 = new HashMap<>();
        Map<String, Integer> goto38 = new HashMap<>();
        action38.put("=", "s36");
        ACTION.put(38, action38);
        GOTO.put(38, goto38);

        // ESTADO 39
        Map<String, String> action39 = new HashMap<>();
        Map<String, Integer> goto39 = new HashMap<>();
        action39.put("(", "s17");
        action39.put("bool", "s16");
        action39.put("id", "s14");
        action39.put("if", "s6");
        action39.put("let", "s22");
        action39.put("num", "s15");
        goto39.put("Add", 10);
        goto39.put("App", 12);
        goto39.put("Atom", 13);
        goto39.put("Comp", 9);
        goto39.put("Expr", 53);
        goto39.put("Factor", 11);
        goto39.put("Logic", 8);
        goto39.put("Pipe", 7);
        ACTION.put(39, action39);
        GOTO.put(39, goto39);

        // ESTADO 40
        Map<String, String> action40 = new HashMap<>();
        Map<String, Integer> goto40 = new HashMap<>();
        action40.put("&&", "s25");
        action40.put(")", "r12");
        action40.put(";;", "r12");
        action40.put("else", "r12");
        action40.put("in", "r12");
        action40.put("then", "r12");
        action40.put("|>", "r12");
        action40.put("||", "s26");
        ACTION.put(40, action40);
        GOTO.put(40, goto40);

        // ESTADO 41
        Map<String, String> action41 = new HashMap<>();
        Map<String, Integer> goto41 = new HashMap<>();
        action41.put("&&", "r14");
        action41.put(")", "r14");
        action41.put(";;", "r14");
        action41.put("else", "r14");
        action41.put("in", "r14");
        action41.put("then", "r14");
        action41.put("|>", "r14");
        action41.put("||", "r14");
        ACTION.put(41, action41);
        GOTO.put(41, goto41);

        // ESTADO 42
        Map<String, String> action42 = new HashMap<>();
        Map<String, Integer> goto42 = new HashMap<>();
        action42.put("&&", "r15");
        action42.put(")", "r15");
        action42.put(";;", "r15");
        action42.put("else", "r15");
        action42.put("in", "r15");
        action42.put("then", "r15");
        action42.put("|>", "r15");
        action42.put("||", "r15");
        ACTION.put(42, action42);
        GOTO.put(42, goto42);

        // ESTADO 43
        Map<String, String> action43 = new HashMap<>();
        Map<String, Integer> goto43 = new HashMap<>();
        action43.put("&&", "r17");
        action43.put(")", "r17");
        action43.put("+", "s29");
        action43.put("-", "s30");
        action43.put(";;", "r17");
        action43.put("else", "r17");
        action43.put("in", "r17");
        action43.put("then", "r17");
        action43.put("|>", "r17");
        action43.put("||", "r17");
        ACTION.put(43, action43);
        GOTO.put(43, goto43);

        // ESTADO 44
        Map<String, String> action44 = new HashMap<>();
        Map<String, Integer> goto44 = new HashMap<>();
        action44.put("&&", "r18");
        action44.put(")", "r18");
        action44.put("+", "s29");
        action44.put("-", "s30");
        action44.put(";;", "r18");
        action44.put("else", "r18");
        action44.put("in", "r18");
        action44.put("then", "r18");
        action44.put("|>", "r18");
        action44.put("||", "r18");
        ACTION.put(44, action44);
        GOTO.put(44, goto44);

        // ESTADO 45
        Map<String, String> action45 = new HashMap<>();
        Map<String, Integer> goto45 = new HashMap<>();
        action45.put("&&", "r20");
        action45.put(")", "r20");
        action45.put("*", "s31");
        action45.put("+", "r20");
        action45.put("-", "r20");
        action45.put(";;", "r20");
        action45.put("<", "r20");
        action45.put("==", "r20");
        action45.put("else", "r20");
        action45.put("in", "r20");
        action45.put("then", "r20");
        action45.put("|>", "r20");
        action45.put("||", "r20");
        ACTION.put(45, action45);
        GOTO.put(45, goto45);

        // ESTADO 46
        Map<String, String> action46 = new HashMap<>();
        Map<String, Integer> goto46 = new HashMap<>();
        action46.put("&&", "r21");
        action46.put(")", "r21");
        action46.put("*", "s31");
        action46.put("+", "r21");
        action46.put("-", "r21");
        action46.put(";;", "r21");
        action46.put("<", "r21");
        action46.put("==", "r21");
        action46.put("else", "r21");
        action46.put("in", "r21");
        action46.put("then", "r21");
        action46.put("|>", "r21");
        action46.put("||", "r21");
        ACTION.put(46, action46);
        GOTO.put(46, goto46);

        // ESTADO 47
        Map<String, String> action47 = new HashMap<>();
        Map<String, Integer> goto47 = new HashMap<>();
        action47.put("&&", "r23");
        action47.put("(", "s17");
        action47.put(")", "r23");
        action47.put("*", "r23");
        action47.put("+", "r23");
        action47.put("-", "r23");
        action47.put(";;", "r23");
        action47.put("<", "r23");
        action47.put("==", "r23");
        action47.put("bool", "s16");
        action47.put("else", "r23");
        action47.put("id", "s14");
        action47.put("in", "r23");
        action47.put("num", "s15");
        action47.put("then", "r23");
        action47.put("|>", "r23");
        action47.put("||", "r23");
        goto47.put("Atom", 32);
        ACTION.put(47, action47);
        GOTO.put(47, goto47);

        // ESTADO 48
        Map<String, String> action48 = new HashMap<>();
        Map<String, Integer> goto48 = new HashMap<>();
        action48.put("&&", "r30");
        action48.put("(", "r30");
        action48.put(")", "r30");
        action48.put("*", "r30");
        action48.put("+", "r30");
        action48.put("-", "r30");
        action48.put(";;", "r30");
        action48.put("<", "r30");
        action48.put("==", "r30");
        action48.put("bool", "r30");
        action48.put("else", "r30");
        action48.put("id", "r30");
        action48.put("in", "r30");
        action48.put("num", "r30");
        action48.put("then", "r30");
        action48.put("|>", "r30");
        action48.put("||", "r30");
        ACTION.put(48, action48);
        GOTO.put(48, goto48);

        // ESTADO 49
        Map<String, String> action49 = new HashMap<>();
        Map<String, Integer> goto49 = new HashMap<>();
        action49.put("(", "s17");
        action49.put("bool", "s16");
        action49.put("id", "s14");
        action49.put("if", "s6");
        action49.put("let", "s22");
        action49.put("num", "s15");
        goto49.put("Add", 10);
        goto49.put("App", 12);
        goto49.put("Atom", 13);
        goto49.put("Comp", 9);
        goto49.put("Expr", 54);
        goto49.put("Factor", 11);
        goto49.put("Logic", 8);
        goto49.put("Pipe", 7);
        ACTION.put(49, action49);
        GOTO.put(49, goto49);

        // ESTADO 50
        Map<String, String> action50 = new HashMap<>();
        Map<String, Integer> goto50 = new HashMap<>();
        action50.put("=", "r7");
        action50.put("id", "r7");
        ACTION.put(50, action50);
        GOTO.put(50, goto50);

        // ESTADO 51
        Map<String, String> action51 = new HashMap<>();
        Map<String, Integer> goto51 = new HashMap<>();
        action51.put("in", "s55");
        ACTION.put(51, action51);
        GOTO.put(51, goto51);

        // ESTADO 52
        Map<String, String> action52 = new HashMap<>();
        Map<String, Integer> goto52 = new HashMap<>();
        action52.put("=", "s56");
        action52.put("id", "s50");
        ACTION.put(52, action52);
        GOTO.put(52, goto52);

        // ESTADO 53
        Map<String, String> action53 = new HashMap<>();
        Map<String, Integer> goto53 = new HashMap<>();
        action53.put("else", "s57");
        ACTION.put(53, action53);
        GOTO.put(53, goto53);

        // ESTADO 54
        Map<String, String> action54 = new HashMap<>();
        Map<String, Integer> goto54 = new HashMap<>();
        action54.put(";;", "s58");
        ACTION.put(54, action54);
        GOTO.put(54, goto54);

        // ESTADO 55
        Map<String, String> action55 = new HashMap<>();
        Map<String, Integer> goto55 = new HashMap<>();
        action55.put("(", "s17");
        action55.put("bool", "s16");
        action55.put("id", "s14");
        action55.put("if", "s6");
        action55.put("let", "s22");
        action55.put("num", "s15");
        goto55.put("Add", 10);
        goto55.put("App", 12);
        goto55.put("Atom", 13);
        goto55.put("Comp", 9);
        goto55.put("Expr", 59);
        goto55.put("Factor", 11);
        goto55.put("Logic", 8);
        goto55.put("Pipe", 7);
        ACTION.put(55, action55);
        GOTO.put(55, goto55);

        // ESTADO 56
        Map<String, String> action56 = new HashMap<>();
        Map<String, Integer> goto56 = new HashMap<>();
        action56.put("(", "s17");
        action56.put("bool", "s16");
        action56.put("id", "s14");
        action56.put("if", "s6");
        action56.put("let", "s22");
        action56.put("num", "s15");
        goto56.put("Add", 10);
        goto56.put("App", 12);
        goto56.put("Atom", 13);
        goto56.put("Comp", 9);
        goto56.put("Expr", 60);
        goto56.put("Factor", 11);
        goto56.put("Logic", 8);
        goto56.put("Pipe", 7);
        ACTION.put(56, action56);
        GOTO.put(56, goto56);

        // ESTADO 57
        Map<String, String> action57 = new HashMap<>();
        Map<String, Integer> goto57 = new HashMap<>();
        action57.put("(", "s17");
        action57.put("bool", "s16");
        action57.put("id", "s14");
        action57.put("if", "s6");
        action57.put("let", "s22");
        action57.put("num", "s15");
        goto57.put("Add", 10);
        goto57.put("App", 12);
        goto57.put("Atom", 13);
        goto57.put("Comp", 9);
        goto57.put("Expr", 61);
        goto57.put("Factor", 11);
        goto57.put("Logic", 8);
        goto57.put("Pipe", 7);
        ACTION.put(57, action57);
        GOTO.put(57, goto57);

        // ESTADO 58
        Map<String, String> action58 = new HashMap<>();
        Map<String, Integer> goto58 = new HashMap<>();
        action58.put("(", "r4");
        action58.put("bool", "r4");
        action58.put("id", "r4");
        action58.put("if", "r4");
        action58.put("let", "r4");
        action58.put("num", "r4");
        action58.put("$", "r4");
        ACTION.put(58, action58);
        GOTO.put(58, goto58);

        // ESTADO 59
        Map<String, String> action59 = new HashMap<>();
        Map<String, Integer> goto59 = new HashMap<>();
        action59.put(")", "r9");
        action59.put(";;", "r9");
        action59.put("else", "r9");
        action59.put("in", "r9");
        action59.put("then", "r9");
        ACTION.put(59, action59);
        GOTO.put(59, goto59);

        // ESTADO 60
        Map<String, String> action60 = new HashMap<>();
        Map<String, Integer> goto60 = new HashMap<>();
        action60.put(";;", "s62");
        ACTION.put(60, action60);
        GOTO.put(60, goto60);

        // ESTADO 61
        Map<String, String> action61 = new HashMap<>();
        Map<String, Integer> goto61 = new HashMap<>();
        action61.put(")", "r10");
        action61.put(";;", "r10");
        action61.put("else", "r10");
        action61.put("in", "r10");
        action61.put("then", "r10");
        ACTION.put(61, action61);
        GOTO.put(61, goto61);

        // ESTADO 62
        Map<String, String> action62 = new HashMap<>();
        Map<String, Integer> goto62 = new HashMap<>();
        action62.put("(", "r5");
        action62.put("bool", "r5");
        action62.put("id", "r5");
        action62.put("if", "r5");
        action62.put("let", "r5");
        action62.put("num", "r5");
        action62.put("$", "r5");
        ACTION.put(62, action62);
        GOTO.put(62, goto62);

        producciones.put(1, new Produccion("Program", 1)); // 1. Program -> Decls
        producciones.put(2, new Produccion("Decls", 2));   // 2. Decls -> Decls Decl
        producciones.put(3, new Produccion("Decls", 1));   // 3. Decls -> Decl
        producciones.put(4, new Produccion("Decl", 6));    // 4. Decl -> let id Params = Expr ;;
        producciones.put(5, new Produccion("Decl", 7));    // 5. Decl -> let rec id Params = Expr ;;
        producciones.put(6, new Produccion("Decl", 2));    // 6. Decl -> Expr ;;
        producciones.put(7, new Produccion("Params", 2));  // 7. Params -> Params id
        producciones.put(8, new Produccion("Params", 1));  // 8. Params -> id
        producciones.put(9, new Produccion("Expr", 6));    // 9. Expr -> let id = Expr in Expr
        producciones.put(10, new Produccion("Expr", 6));   // 10. Expr -> if Expr then Expr else Expr
        producciones.put(11, new Produccion("Expr", 1));   // 11. Expr -> Pipe
        producciones.put(12, new Produccion("Pipe", 3));   // 12. Pipe -> Pipe |> Logic
        producciones.put(13, new Produccion("Pipe", 1));   // 13. Pipe -> Logic
        producciones.put(14, new Produccion("Logic", 3));  // 14. Logic -> Logic && Comp
        producciones.put(15, new Produccion("Logic", 3));  // 15. Logic -> Logic || Comp
        producciones.put(16, new Produccion("Logic", 1));  // 16. Logic -> Comp
        producciones.put(17, new Produccion("Comp", 3));   // 17. Comp -> Add == Add
        producciones.put(18, new Produccion("Comp", 3));   // 18. Comp -> Add < Add
        producciones.put(19, new Produccion("Comp", 1));   // 19. Comp -> Add
        producciones.put(20, new Produccion("Add", 3));    // 20. Add -> Add + Factor
        producciones.put(21, new Produccion("Add", 3));    // 21. Add -> Add - Factor
        producciones.put(22, new Produccion("Add", 1));    // 22. Add -> Factor
        producciones.put(23, new Produccion("Factor", 3)); // 23. Factor -> Factor * App
        producciones.put(24, new Produccion("Factor", 1)); // 24. Factor -> App
        producciones.put(25, new Produccion("App", 2));    // 25. App -> App Atom
        producciones.put(26, new Produccion("App", 1));    // 26. App -> Atom
        producciones.put(27, new Produccion("Atom", 1));   // 27. Atom -> id
        producciones.put(28, new Produccion("Atom", 1));   // 28. Atom -> num
        producciones.put(29, new Produccion("Atom", 1));   // 29. Atom -> bool
        producciones.put(30, new Produccion("Atom", 3));   // 30. Atom -> ( Expr )
    }


    
    // Algoritmo Principal SLR(1)
    public boolean analizar(String[] tokens) {
        Stack<Integer> pilaEstados = new Stack<>();
        Stack<String> pilaSimbolos = new Stack<>();

        // Pila para construir el arbol sintactico
        NodoAST arbol = new NodoAST();
        Stack<NodoAST.Nodo> pilaAST = new Stack<>();

        // Estado inicial
        pilaEstados.push(0);

        // Agregar marcador de fin de cadena
        List<String> entrada = new ArrayList<>();

        for(String token : tokens) {
            if(token == null || token.isEmpty() || token.equals("$")) {
                System.out.println("Error: token invalido.");
                return false;
            }
            entrada.add(token);
        }

        entrada.add("$");

        int posicion = 0;

        // Ciclo Shift / Reduce
        while (true) {
            int estado = pilaEstados.peek();
            String tokenActual = entrada.get(posicion);

            // Consultar tabla ACTION
            String accion = obtenerAccion(estado, tokenActual);

            System.out.println("\nEstado: " + estado);
            System.out.println("Token: " + tokenActual);
            System.out.println("Accion: " + accion);

            // Error sintactico de token
            if (accion == null || accion.isEmpty()) {
                System.out.println("Error sintactico en el token: " + tokenActual);
                return false;
            }

            // Shift
            if (accion.startsWith("s")) {
                int siguienteEstado;

                try {
                    siguienteEstado = Integer.parseInt(accion.substring(1));
                } catch (NumberFormatException e) {
                    System.out.println("Accion SHIFT invalida.");
                    return false;
                }

                if (tokenActual.equals("$")) {
                    System.out.println("Error: SHIFT sobre $.");
                    return false;
                }

                pilaSimbolos.push(tokenActual);
                pilaEstados.push(siguienteEstado);

                // Crear nodo terminal
                NodoAST.Nodo nodo = arbol.new Nodo(tokenActual, new NodoAST.Nodo[0]);
                pilaAST.push(nodo);
                posicion++;
                System.out.println("SHIFT al estado " + siguienteEstado);
            }

            // REDUCE
            else if (accion.startsWith("r")) {
                int numeroProduccion;

                try {
                    numeroProduccion = Integer.parseInt(accion.substring(1));
                } catch (NumberFormatException e) {
                    System.out.println("Accion REDUCE invalida.");
                    return false;
                }

                Produccion produccion = producciones.get(numeroProduccion);

                if (produccion == null) {
                    System.out.println("Error: produccion no encontrada: " + numeroProduccion);
                    return false;
                }

                int cantidad = produccion.longitudDerecha;

                // Verificar que existan elementos suficientes para reducir
                if(cantidad < 0 || cantidad > pilaSimbolos.size() || cantidad > pilaAST.size() || cantidad >= pilaEstados.size()) {
                    System.out.println("Error: reduccion invalida.");
                    return false;
                }

                // Hijos de la nueva produccion
                NodoAST.Nodo[] hijos = new NodoAST.Nodo[cantidad];

                // Desapilar simbolos, estados y nodos
                for (int i = cantidad - 1; i >= 0; i--) {
                    pilaSimbolos.pop();
                    pilaEstados.pop();

                    // Conservar el orden original
                    hijos[i] = pilaAST.pop();
                }

                // Crear nuevo nodo no terminal
                NodoAST.Nodo nuevoNodo = arbol.new Nodo(produccion.izquierda, hijos);

                // Consultar GOTO
                int estadoActual = pilaEstados.peek();

                Integer estadoGoto = obtenerGoto(estadoActual, produccion.izquierda);

                if (estadoGoto == null) {
                    System.out.println("Error: GOTO no encontrado.");
                    return false;
                }

                // Apilar el no terminal reducido
                pilaSimbolos.push(produccion.izquierda);
                pilaEstados.push(estadoGoto);
                pilaAST.push(nuevoNodo);

                System.out.println("REDUCE por regla " + numeroProduccion + ": " + produccion.izquierda);
                System.out.println("GOTO al estado " + estadoGoto);
            }

            else if (accion.equalsIgnoreCase("acc")) {
                if (pilaAST.size() == 1) {
                    System.out.println("\n===== ARBOL SINTACTICO =====");
                    arbol.imprimir(pilaAST.peek());
                }
                return true;
            }
            // Accion Desconocida
            else {
                System.out.println("Error: accion desconocida: " + accion);
                return false;
            }
        }
    }

    // Consultar Tabla ACTION
    private String obtenerAccion(int estado, String token) {
        Map<String, String> fila = ACTION.get(estado);

        if (fila == null) {
            return null;
        }

        return fila.get(token);
    }

    // Consultar Tabla GOTO
    private Integer obtenerGoto(int estado, String noTerminal) {

        Map<String, Integer> fila = GOTO.get(estado);

        if (fila == null) {
            return null;
        }

        return fila.get(noTerminal);
    }
}
