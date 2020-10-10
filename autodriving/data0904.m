%front
clear
clc
Read2 = importdata('test012.txt');
readr=1:60;
Read1 = [readr Read2']
Num=length(Read1);
X1= 1:Num;
x=1:Num/7;
interval=Num/7;
velocity=[];
ymean=mean(Read1);
for each=1:Num
   if (Read1(each)<80)
       Read1(each)=ymean;
   end
end
for each2=1:interval
    if (each2>0 & each2<31)
        ttest=each2*3+130;
        velocity=[velocity ttest];
    elseif (each2>30 & each2<81)
        ttest=220-(each2-30)*2;
        velocity=[velocity ttest];
    else
        ttest=(each2-80)*4+130;
        velocity=[velocity ttest];
    end
end
y1= Read1(Num*1/7+1:Num*2/7);
aa=100;
term=10;
AVG20=[mean(Read1(aa+1:aa+term*1)) mean(Read1(aa+term*1+1:aa+term*2)) mean(Read1(aa+term*2+1:aa+term*3)) mean(Read1(aa+term*3+1:aa+term*4)) mean(Read1(aa+term*4+1:aa+term*5)) mean(Read1(aa+term*5+1:aa+term*6)) mean(Read1(aa+term*6+1:aa+term*7)) mean(Read1(aa+term*7+1:aa+term*8)) mean(Read1(aa+term*8+1:aa+term*9))  mean(Read1(aa+term*9+1:aa+term*10))]
HOW=1:10;
subplot(3,1,1);
plot(x,velocity)
legend('velocity')
grid on
hold on
subplot(3,1,2);
plot(HOW,AVG20)
grid on
hold on
title('Relation between impact')
xlabel('Oscillation')
ylabel('Average Scale')
legend('trial_1')
subplot(3,1,3);
plot(x,y1)
grid on
hold on
xlabel('Oscillation')
ylabel('Scale')
legend('trial_1')
hold off
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
clc
Read2 = importdata('test012.txt');
readr=1:60;
Read1 = [readr Read2']
Num=length(Read1);
X1= 1:Num;
x=1:Num/7;
interval=Num/7;
velocity=[];
ymean=mean(Read1);
for each=1:Num
   if (Read1(each)<80)
       Read1(each)=ymean;
   end
end
for each2=1:interval
    if (each2>0 & each2<31)
        ttest=each2*3+130;
        velocity=[velocity ttest];
    elseif (each2>30 & each2<81)
        ttest=220-(each2-30)*2;
        velocity=[velocity ttest];
    else
        ttest=(each2-80)*4+130;
        velocity=[velocity ttest];
    end
end
y1= Read1(Num*2/7+1:Num*3/7);
aa=200;
term=10;
AVG20=[mean(Read1(aa+1:aa+term*1)) mean(Read1(aa+term*1+1:aa+term*2)) mean(Read1(aa+term*2+1:aa+term*3)) mean(Read1(aa+term*3+1:aa+term*4)) mean(Read1(aa+term*4+1:aa+term*5)) mean(Read1(aa+term*5+1:aa+term*6)) mean(Read1(aa+term*6+1:aa+term*7)) mean(Read1(aa+term*7+1:aa+term*8)) mean(Read1(aa+term*8+1:aa+term*9))  mean(Read1(aa+term*9+1:aa+term*10))]
HOW=1:10;
subplot(3,1,1);
plot(x,velocity)
legend('velocity')
grid on
hold on
subplot(3,1,2);
plot(HOW,AVG20)
grid on
hold on
title('Relation between impact')
xlabel('Oscillation')
ylabel('Average Scale')
legend('trial_2')
subplot(3,1,3);
plot(x,y1)
grid on
hold on
xlabel('Oscillation')
ylabel('Scale')
legend('trial_2')
hold off
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
clc
Read2 = importdata('test012.txt');
readr=1:60;
Read1 = [readr Read2']
Num=length(Read1);
X1= 1:Num;
x=1:Num/7;
interval=Num/7;
velocity=[];
ymean=mean(Read1);
for each=1:Num
   if (Read1(each)<80)
       Read1(each)=ymean;
   end
end
for each2=1:interval
    if (each2>0 & each2<31)
        ttest=each2*3+130;
        velocity=[velocity ttest];
    elseif (each2>30 & each2<81)
        ttest=220-(each2-30)*2;
        velocity=[velocity ttest];
    else
        ttest=(each2-80)*4+130;
        velocity=[velocity ttest];
    end
end
y1= Read1(Num*3/7+1:Num*4/7);
aa=300;
term=10;
AVG20=[mean(Read1(aa+1:aa+term*1)) mean(Read1(aa+term*1+1:aa+term*2)) mean(Read1(aa+term*2+1:aa+term*3)) mean(Read1(aa+term*3+1:aa+term*4)) mean(Read1(aa+term*4+1:aa+term*5)) mean(Read1(aa+term*5+1:aa+term*6)) mean(Read1(aa+term*6+1:aa+term*7)) mean(Read1(aa+term*7+1:aa+term*8)) mean(Read1(aa+term*8+1:aa+term*9))  mean(Read1(aa+term*9+1:aa+term*10))]
HOW=1:10;
subplot(3,1,1);
plot(x,velocity)
legend('velocity')
grid on
hold on
subplot(3,1,2);
plot(HOW,AVG20)
grid on
hold on
title('Relation between impact')
xlabel('Oscillation')
ylabel('Average Scale')
legend('trial_3')
subplot(3,1,3);
plot(x,y1)
grid on
hold on
xlabel('Oscillation')
ylabel('Scale')
legend('trial_3')
hold off
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
clc
Read2 = importdata('test012.txt');
readr=1:60;
Read1 = [readr Read2']
Num=length(Read1);
X1= 1:Num;
x=1:Num/7;
interval=Num/7;
velocity=[];
ymean=mean(Read1);
for each=1:Num
   if (Read1(each)<80)
       Read1(each)=ymean;
   end
end
for each2=1:interval
    if (each2>0 & each2<31)
        ttest=each2*3+130;
        velocity=[velocity ttest];
    elseif (each2>30 & each2<81)
        ttest=220-(each2-30)*2;
        velocity=[velocity ttest];
    else
        ttest=(each2-80)*4+130;
        velocity=[velocity ttest];
    end
end
y1= Read1(Num*4/7+1:Num*5/7);
aa=400;
term=10;
AVG20=[mean(Read1(aa+1:aa+term*1)) mean(Read1(aa+term*1+1:aa+term*2)) mean(Read1(aa+term*2+1:aa+term*3)) mean(Read1(aa+term*3+1:aa+term*4)) mean(Read1(aa+term*4+1:aa+term*5)) mean(Read1(aa+term*5+1:aa+term*6)) mean(Read1(aa+term*6+1:aa+term*7)) mean(Read1(aa+term*7+1:aa+term*8)) mean(Read1(aa+term*8+1:aa+term*9))  mean(Read1(aa+term*9+1:aa+term*10))]
HOW=1:10;
subplot(3,1,1);
plot(x,velocity)
legend('velocity')
grid on
hold on
subplot(3,1,2);
plot(HOW,AVG20)
grid on
hold on
title('Relation between impact')
xlabel('Oscillation')
ylabel('Average Scale')
legend('trial_4')
subplot(3,1,3);
plot(x,y1)
grid on
hold on
xlabel('Oscillation')
ylabel('Scale')
legend('trial_4')
hold off
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
clc
Read2 = importdata('test012.txt');
readr=1:60;
Read1 = [readr Read2']
Num=length(Read1);
X1= 1:Num;
x=1:Num/7;
interval=Num/7;
velocity=[];
ymean=mean(Read1);
for each=1:Num
   if (Read1(each)<80)
       Read1(each)=ymean;
   end
end
for each2=1:interval
    if (each2>0 & each2<31)
        ttest=each2*3+130;
        velocity=[velocity ttest];
    elseif (each2>30 & each2<81)
        ttest=220-(each2-30)*2;
        velocity=[velocity ttest];
    else
        ttest=(each2-80)*4+130;
        velocity=[velocity ttest];
    end
end
y1= Read1(Num*5/7+1:Num*6/7);
aa=500;
term=10;
AVG20=[mean(Read1(aa+1:aa+term*1)) mean(Read1(aa+term*1+1:aa+term*2)) mean(Read1(aa+term*2+1:aa+term*3)) mean(Read1(aa+term*3+1:aa+term*4)) mean(Read1(aa+term*4+1:aa+term*5)) mean(Read1(aa+term*5+1:aa+term*6)) mean(Read1(aa+term*6+1:aa+term*7)) mean(Read1(aa+term*7+1:aa+term*8)) mean(Read1(aa+term*8+1:aa+term*9))  mean(Read1(aa+term*9+1:aa+term*10))]
HOW=1:10;
subplot(3,1,1);
plot(x,velocity)
legend('velocity')
grid on
hold on
subplot(3,1,2);
plot(HOW,AVG20)
grid on
hold on
title('Relation between impact')
xlabel('Oscillation')
ylabel('Average Scale')
legend('trial_5')
subplot(3,1,3);
plot(x,y1)
grid on
hold on
xlabel('Oscillation')
ylabel('Scale')
legend('trial_5')
hold off
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
clc
Read2 = importdata('test012.txt');
readr=1:60;
Read1 = [readr Read2']
Num=length(Read1);
X1= 1:Num;
x=1:Num/7;
interval=Num/7;
velocity=[];
ymean=mean(Read1);
for each=1:Num
   if (Read1(each)<80)
       Read1(each)=ymean;
   end
end
for each2=1:interval
    if (each2>0 & each2<31)
        ttest=each2*3+130;
        velocity=[velocity ttest];
    elseif (each2>30 & each2<81)
        ttest=220-(each2-30)*2;
        velocity=[velocity ttest];
    else
        ttest=(each2-80)*4+130;
        velocity=[velocity ttest];
    end
end
y1= Read1(Num*6/7+1:Num);
aa=600;
term=10;
AVG20=[mean(Read1(aa+1:aa+term*1)) mean(Read1(aa+term*1+1:aa+term*2)) mean(Read1(aa+term*2+1:aa+term*3)) mean(Read1(aa+term*3+1:aa+term*4)) mean(Read1(aa+term*4+1:aa+term*5)) mean(Read1(aa+term*5+1:aa+term*6)) mean(Read1(aa+term*6+1:aa+term*7)) mean(Read1(aa+term*7+1:aa+term*8)) mean(Read1(aa+term*8+1:aa+term*9))  mean(Read1(aa+term*9+1:aa+term*10))]
HOW=1:10;
subplot(3,1,1);
plot(x,velocity)
legend('velocity')
grid on
hold on
subplot(3,1,2);
plot(HOW,AVG20)
grid on
hold on
title('Relation between impact')
xlabel('Oscillation')
ylabel('Average Scale')
legend('trial_1','trial_2','trial_3','trial_4','trial_5','trial_6')
subplot(3,1,3);
plot(x,y1)
grid on
hold on
xlabel('Oscillation')
ylabel('Scale')
legend('trial_1','trial_2','trial_3','trial_4','trial_5','trial_6')
hold off