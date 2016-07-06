
public String sequenceGenerator(){
                int start = 9999;
                String result = ""+start;
                Set<Integer> visited = new HashSet<>();
                visited.add(start);
                while(visited.size()<10000){
                        int next = start*10%10000;
                        int i=0;
                        for(;i<=9;i++){
                                if (!visited.contains(next+i)) break;
                        }
                        visited.add(next+i);
                        start = next+i;
                        result+=i;
                }
                return result;
                
        }
  
  public static String getPass(HashSet<Integer> visit) {
                int num = 9999;
                int count = 0;
                StringBuilder sb = new StringBuilder("999");
                while (visit.size() < 10000) {
                        sb.append(num % 10);
                        visit.add(num);
                        num *= 10;
                        num %= 10000;
                        int add = 0;
                        while (add < 9) {
                                if (!visit.contains(num + add)) {
                                        break;
                                }
                                add++;
                        }
                        num += (add % 10);
                        count++;
                }
                System.out.println(count);
                return sb.toString();
        }

        public static void main(String[] args) {
                HashSet<Integer> visited = new HashSet<Integer>(0);
                String s = getPass(visited);
                System.out.println(s);
                System.out.println("length:" + s.length());
                for (int i = 0 ; i < 10000; i++) {
                        String num = Integer.toString(i);        
                        //refill the string, e.g:39->0039
                        if (num.length() < 4) {
                                StringBuilder sb = new StringBuilder(num);
                                for (int j = 0; j < 4 - num.length(); j++) {
                                        sb.insert(0, "0");
                                }
                                num = sb.toString();
                        }
                        if (s.indexOf(num) == -1) {
                                System.out.println("false");
                        }
                }        
        }


