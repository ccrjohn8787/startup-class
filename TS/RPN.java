
import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public interface IOperator
	{
		public String GetOperatorSign();
		public int operate(int left, int right);
	}
	public class Add implements IOperator
	{
		@Override
		public String GetOperatorSign()
		{
			return "+";
		}
		@Override
		public int operate(int left, int right)
		{
			return left + right;
		}
	}
	
	public class Multiply implements IOperator
	{
		@Override
		public String GetOperatorSign()
		{
			return "*";
		}
		@Override
		public int operate(int left, int right)
		{
			return left * right;
		}
	}
	
	public class OperatorProvider
	{
		HashMap<String, IOperator> map = new HashMap<String, IOperator>();
		static OperatorProvider op = null;
		static Object lock = new Object();
		public static OperatorProvider getInstance()
		{
			if (op == null)
			{
				synchronized(lock)
				{
					if (op == null)
					{
						op = new OperatorProvider();
					}
				}
			}
			return op;
		}
		public int operate(int left, int right, String op)
		{
			// might be better to throw exception here
			IOperator operator = map.get(op);
			return operator.operate(left, right);
		}
		public void register(String operatorStr, IOperator operator)
		{
			map.put(operatorStr, operator);
		}
		
		public boolean supports(String operatorStr)
		{
			return map.containsKey(operatorStr);
		}
	}
	
	public class RpnEvaluator
	{
		OperatorProvider or;
		public RpnEvaluator(OperatorProvider operatorRegister)
		{
			this.or = operatorRegister;
		}
		public int evaluate(String[] tokens)
		{
			Stack<Integer> stack = new Stack<Integer>();
			for (int i = 0; i < tokens.length; i++)
			{
				if (isInt(tokens[i]))
				{
					stack.push(Integer.parseInt(tokens[i]));
				}
				else
				{
					int right = stack.pop();
					int left = stack.pop();
					stack.push(or.operate(left, right));
				}
			}
			return stack.peek();
		}
		
		private boolean isInt(String str)
		 {
		     try
		     {
		         Integer.parseInt(str);
		         return true;
		     }
		     catch(NumberFormatException nfe)
		     {
		         return false;
		     }
		 }
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		// Factory pattern: http://www.lintcode.com/en/problem/toy-factory/
		// Singleton pattern: http://www.lintcode.com/en/problem/singleton/
		OperatorProvider op = OperatorProvider.getInstance();
		op.register("+", new Add());
		op.register("*", new Multiply());
		RpnEvaluator ev = new RpnEvaluator(op);
		String[] input = {"9", "3", "+"};
		System.out.println(ev.evaluate(input));
	}
}
