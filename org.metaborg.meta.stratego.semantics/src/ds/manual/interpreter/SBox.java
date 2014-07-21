/**
 * 
 */
package ds.manual.interpreter;

import ds.generated.interpreter.I_Thunk;

/**
 * @author vladvergu
 *
 */
public class SBox {

	public I_Thunk thunk;

	public SBox() {
	}
	
	public SBox(I_Thunk thunk) {
		this.thunk = thunk;
	}
}
