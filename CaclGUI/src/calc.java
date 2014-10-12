import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import CISC181.Calcengine.Calculator;


public class calc {

	protected Shell shlFutureValueCalculator;
	private Text curVal;
	private Text interest;
	private Text numYears;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			calc window = new calc();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlFutureValueCalculator.open();
		shlFutureValueCalculator.layout();
		while (!shlFutureValueCalculator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlFutureValueCalculator = new Shell();
		shlFutureValueCalculator.setSize(199, 200);
		shlFutureValueCalculator.setText("Future Value Calculator");
		shlFutureValueCalculator.setLayout(null);
		
		Label lblNewLabel = new Label(shlFutureValueCalculator, SWT.NONE);
		lblNewLabel.setBounds(1, 5, 97, 14);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setText("Current Value: $");
		
		curVal = new Text(shlFutureValueCalculator, SWT.BORDER);
		curVal.setBounds(104, 0, 64, 19);
		
		Label lblNewLabel_1 = new Label(shlFutureValueCalculator, SWT.NONE);
		lblNewLabel_1.setBounds(23, 29, 75, 14);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setText("Interest Rate:");
		
		interest = new Text(shlFutureValueCalculator, SWT.BORDER);
		interest.setBounds(104, 24, 64, 19);
		
		Label label = new Label(shlFutureValueCalculator, SWT.NONE);
		label.setBounds(173, 26, 12, 14);
		label.setText("%");
		
		Label lblNewLabel_2 = new Label(shlFutureValueCalculator, SWT.NONE);
		lblNewLabel_2.setBounds(0, 53, 98, 14);
		lblNewLabel_2.setAlignment(SWT.RIGHT);
		lblNewLabel_2.setText("Number of Years:");
		
		numYears = new Text(shlFutureValueCalculator, SWT.BORDER);
		numYears.setBounds(104, 48, 64, 19);
		
		final Label futureValueLabel = new Label(shlFutureValueCalculator, SWT.NONE);
		futureValueLabel.setBounds(1, 107, 184, 48);
		futureValueLabel.setFont(SWTResourceManager.getFont("Times New Roman", 16, SWT.BOLD));
		futureValueLabel.setAlignment(SWT.CENTER);
		futureValueLabel.setText("The Future Value is...");
		
		Button btnNewButton = new Button(shlFutureValueCalculator, SWT.NONE);
		btnNewButton.setBounds(1, 73, 184, 28);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double currentVal,interestRate, numberYears;
				try{
					currentVal=Double.parseDouble(curVal.getText());
				}catch(Exception exc){
					MessageDialog.openError(shlFutureValueCalculator, "Error", "Value in the 'Current Value' Field must be a number" );
					return;
				}
				try{
					interestRate=Double.parseDouble(interest.getText());
				}catch(Exception exc){
					MessageDialog.openError(shlFutureValueCalculator, "Error", "Value in the 'Interest Rate' Field must be a number" );
					return;
				}
				try{
					numberYears=Double.parseDouble(numYears.getText());
				}catch(Exception exc){
					MessageDialog.openError(shlFutureValueCalculator, "Error", "Value in the 'Number of Years' Field must be a number" );
					return;
				}
				double futureVal = Calculator.futureVal(currentVal, interestRate, numberYears);
				futureValueLabel.setText(String.format("The Future Value is %n$%,.2f", futureVal));
				
			}
		});
		btnNewButton.setText("Calculate");

	}
}
