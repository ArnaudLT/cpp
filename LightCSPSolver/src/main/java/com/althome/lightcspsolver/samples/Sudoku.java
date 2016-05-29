/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.althome.lightcspsolver.samples;

import com.althome.lightcspsolver.solver.Solver;
import com.althome.lightcspsolver.solver.constraints.ConstraintFactory;
import com.althome.lightcspsolver.solver.variables.Variable;
import com.althome.lightcspsolver.solver.variables.VariableFactory;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Arnaud
 */
public class Sudoku {
    
    public static void main(String[] args) {
        
        
        // Exemple volé ! (cf. Choco).
        int n = 9;
        Data data = Data.level1;
        
        Solver solver = new Solver();
        
        ArrayList<ArrayList<Variable>> rows = new ArrayList<ArrayList<Variable>>();
        ArrayList<ArrayList<Variable>> cols = new ArrayList<ArrayList<Variable>>();
        ArrayList<ArrayList<Variable>> carres = new ArrayList<ArrayList<Variable>>();
        for (int i = 0; i < n; i++) {
            ArrayList<Variable> oneRow = new ArrayList<Variable>();
            for (int j = 0; j < n; j++) {
                if (data.grid(i, j) > 0) {
                    oneRow.add(VariableFactory.enumerated("f_" + i + "_" + j, data.grid(i, j), data.grid(i, j), solver));
                } else {
                    oneRow.add(VariableFactory.enumerated("c_" + i + "_" + j, 1, n, solver));
                }
            }
            rows.add(oneRow);
        }
        
        for (int i = 0; i < n; i++) {
            ArrayList<Variable> oneCol = new ArrayList<Variable>();
            for (int j = 0; j < n; j++) {
                oneCol.add(rows.get(j).get(i));
            }
            cols.add(oneCol);
        }
/*
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    carres[j + k * 3][i] = rows[k * 3][i + j * 3];
                    carres[j + k * 3][i + 3] = rows[1 + k * 3][i + j * 3];
                    carres[j + k * 3][i + 6] = rows[2 + k * 3][i + j * 3];
                }
            }
        }
*/
        for (int i = 0; i < n; i++) {
            solver.post(ConstraintFactory.allDifferent(rows.get(i)));
            solver.post(ConstraintFactory.allDifferent(cols.get(i)));
            //solver.post(ConstraintFactory.allDifferent(carres.get(i)));
        }
        
        long start, duration, end;
        start = System.currentTimeMillis();
        
        System.out.println(solver.solve());
        
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.print("time = "+duration+" ms");
        
    }
    
    static enum Data {
        level1(
                new int[][]{
                        {0, 0, 0, 2, 0, 0, 0, 0, 0},
                        {0, 8, 0, 0, 3, 0, 0, 7, 0},
                        {3, 0, 0, 5, 0, 4, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 2, 8},
                        {8, 3, 0, 0, 1, 0, 0, 0, 0},
                        {0, 4, 0, 7, 2, 0, 3, 5, 1},
                        {0, 7, 0, 0, 5, 6, 0, 0, 4},
                        {0, 0, 3, 0, 0, 0, 0, 0, 0},
                        {2, 0, 5, 4, 0, 1, 6, 0, 3}
                }
        ),
        level2(
                new int[][]{
                        {3, 0, 4, 0, 2, 0, 0, 7, 0},
                        {1, 5, 0, 0, 0, 0, 0, 4, 0},
                        {0, 0, 0, 0, 0, 1, 0, 8, 3},
                        {0, 0, 0, 0, 0, 6, 1, 0, 0},
                        {2, 0, 5, 0, 3, 0, 0, 0, 8},
                        {7, 0, 0, 1, 0, 0, 3, 0, 0},
                        {0, 0, 0, 0, 0, 0, 6, 0, 0},
                        {5, 6, 0, 0, 0, 7, 0, 0, 0},
                        {0, 0, 0, 8, 0, 0, 0, 1, 4}
                }
        ),
        level3(
                new int[][]{
                        {0, 1, 0, 0, 0, 0, 0, 0, 0},
                        {8, 0, 0, 0, 0, 2, 1, 7, 0},
                        {0, 0, 4, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 6, 0, 1, 3},
                        {0, 5, 3, 0, 7, 0, 6, 0, 2},
                        {1, 0, 0, 8, 0, 0, 5, 4, 0},
                        {0, 0, 0, 3, 1, 5, 0, 2, 6},
                        {0, 4, 0, 2, 0, 0, 0, 0, 7},
                        {0, 0, 0, 4, 8, 0, 3, 0, 0}
                }
        ),
        level4(
                new int[][]{
                        {0, 4, 0, 8, 0, 0, 0, 0, 0},
                        {0, 1, 0, 7, 2, 0, 5, 0, 4},
                        {8, 0, 0, 4, 0, 0, 0, 0, 0},
                        {1, 0, 5, 3, 0, 0, 4, 2, 0},
                        {0, 3, 0, 0, 0, 0, 0, 0, 0},
                        {4, 0, 0, 0, 5, 0, 7, 0, 1},
                        {6, 0, 0, 0, 0, 0, 1, 7, 0},
                        {0, 0, 0, 2, 1, 0, 8, 6, 0},
                        {2, 0, 0, 0, 3, 7, 0, 0, 0}
                }
        ),
        level5(
                new int[][]{
                        {0, 0, 0, 2, 0, 0, 0, 1, 5},
                        {3, 0, 0, 0, 0, 0, 7, 8, 0},
                        {0, 0, 0, 7, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 0, 0, 5, 7},
                        {7, 2, 0, 0, 4, 0, 0, 0, 0},
                        {8, 6, 0, 1, 0, 3, 0, 4, 0},
                        {4, 0, 0, 0, 1, 0, 0, 0, 0},
                        {2, 1, 0, 0, 0, 7, 8, 3, 0},
                        {0, 5, 0, 3, 0, 0, 0, 0, 0}
                }
        ),
        level6(
                new int[][]{
                        {0, 0, 0, 1, 0, 5, 4, 0, 0},
                        {0, 6, 0, 2, 0, 8, 0, 0, 7},
                        {0, 5, 2, 0, 0, 0, 1, 0, 0},
                        {0, 1, 5, 6, 0, 2, 0, 0, 0},
                        {2, 0, 0, 0, 0, 7, 5, 1, 0},
                        {0, 7, 8, 4, 0, 0, 0, 3, 2},
                        {0, 0, 3, 0, 1, 4, 7, 0, 6},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {6, 0, 0, 5, 0, 0, 0, 8, 0}
                }
        ),;
        final int[][] grid;

        Data(int[][] grid) {
            this.grid = grid;
        }

        int grid(int i, int j) {
            return grid[i][j];
        }
    }
    
}
