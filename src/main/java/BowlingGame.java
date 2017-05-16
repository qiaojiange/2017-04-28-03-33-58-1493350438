public class BowlingGame {




    public int getBowlingScore(String bowlingCode) throws Exception {

        String str[] = bowlingCode.split("\\|\\|");
        //统计每一次击倒瓶子的个数
        int[] frameCount = new int[22];
        for (int i = 0; i < frameCount.length; i++) {
            frameCount[i] = -1;
        }

        //额外的机会
        if (str.length == 1){

        }else{
            getAddBoolingCount(str[1],frameCount);
        }

        String[] frameStrs = str[0].split("\\|");


        int sum = 0;
        for(int i = 9;i>=0;i--){
//            printf(frameCount);
            sum += getScore(frameStrs[i],i,frameCount);
        }

        return sum;
    }

    private void printf(int[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i:arr){
            sb.append(i+",");
        }
        System.out.println(sb.toString());
    }
    private int getScore(String frameStr, int i, int[] frameCount) throws Exception {
        char ch1 = '0';
        char ch2 = '0';
//        1.先算当前进球个数
//        2.然后结合后面计算分数
        if (frameStr.length() == 1  ){
            if ((ch1=frameStr.charAt(0))=='X'){
                frameCount[2*i] = 10;
            }else{
                throw new Exception("invalide date!");
            }

        }else{
//==2
            ch1 = frameStr.charAt(0);
            ch2 = frameStr.charAt(1);
            if(ch1 >='0' && ch1<='9' ){
                frameCount[2*i] = ch1-'0';
            }else if( '-' == ch1 ){
                frameCount[2*i] = 0;
            }

            if(ch2 >='0' && ch2<='9'){
                frameCount[2*i+1] = ch2-'0';
            }else if(ch2 == '-'){
                frameCount[2*i+1] = 0;
            }else if(ch2 == '/'){
                frameCount[2*i+1] = 10-frameCount[2*i];
            }

        }

        int sum = 0;
        if(ch1 == 'X'){
            sum = 10;
            int next = 2*(i+1);
            for(int j = 0;j<2;++j){
                while(frameCount[next] ==-1){
                    next++;
                }
                sum += frameCount[next];
                next++;
            }
         //   sum += frameCount[next*2+1];
            return sum;
        }


        if(ch2 == '/'){
            int next =2*(i+1);
            sum = 10;
            while(frameCount[next] ==-1){
                next++;
            }
            sum += frameCount[next];
            return sum;
        }else{
            if(ch1>='0' && ch1<='9'){
                sum += (ch1-'0');
            }
            if(ch2>='0' && ch2<='9'){
                sum += (ch2-'0');
            }
            return sum;
        }

    }

    //    获取额外的分数
    public int getAddScore(String s) {
        int sum = 0;
        for(int i = 0;i<s.length();++i){
            char ch = s.charAt(i);
            if (ch =='X'){
                sum += 10;
            }else{
                sum += ch-'0';
            }
        }
        return sum;
    }

//    获取额外的进球个数
    public void  getAddBoolingCount(String s, int[] frameCount){
        char ch1 = s.charAt(0);
        char ch2 = '0' ;
        if (s.length() == 2){
            ch2 = s.charAt(1);

            if (ch2 == 'X'){
                frameCount[21] = 10;
            }else{
                frameCount[21] = ch2-'0';
            }
        }

        if (ch1 == 'X'){
            frameCount[20] = 10;
        }else{
            frameCount[20] = ch1-'0';
        }

    }
}
