/**
 * 
 */
package ds.manual.interpreter;

import ds.generated.interpreter.A_Thunk;

/**
 * @author vladvergu
 *
 */
public class SBox {

	public A_Thunk thunk;

	public SBox() {
	}
	
	public SBox(A_Thunk thunk) {
		this.thunk = thunk;
	}
}
