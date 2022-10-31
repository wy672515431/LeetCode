#include<stdio.h>

#define LEFT_WHEEL  1
#define RIGHT_WHEEL 2

#define ADD_VALUE_FB 10
#define ADD_VALUE_LR 11

int function1(int a1,float a2)
{
	return 20;
}
struct pid_s{
    float Kp;
    float Ki;
    float Kd;
};

struct spd {
    float spd_length;
    float spd_length_l;
    float spd_length_r;
    //struct
};

float spd_length = 0.0,spd_length_l = 0.0, spd_length_r = 0.0;
float spd_v = 0;	 //the speed after filter
int last_target = 0;
int PID_Cal_Speed(int current,int target,unsigned char which_wheel)
{
	int temp;
	
	struct pid_s TTQ;
	struct pid_s *p;
	
	p = &TTQ;
	
	p->Kp = 1;
	p->Ki = 1;
	p->Kd = 1;
	
	
	//target = 1;
	if(target == 1)
		current += 2.2;
	else if(target == 2)
		current -= 2.0;
	temp = (int)(current);
	spd_v = spd_v * 4 / 5 + (float)(current) / 5;

	if(target == 0)
	{
		if(LEFT_WHEEL == which_wheel)
			spd_length_l += (int)temp;
		else if(RIGHT_WHEEL == which_wheel)
			spd_length_r += (int)temp;
	}
#define TEMP1_F	5
#define TEMP1_B 2
	if(LEFT_WHEEL == which_wheel)
	{
		if(target == 1)
		{
			spd_length_l += ADD_VALUE_FB;
			if(spd_length_l > TEMP1_F)
				spd_length_l = TEMP1_F;
		}
		else if(target == 2)
		{
			spd_length_l -= ADD_VALUE_FB;
			if(spd_length_l < -TEMP1_B)
				spd_length_l = -TEMP1_B;
		}
		else if(target == 3)
			spd_length_l += ADD_VALUE_LR;
		else if(target == 4)
			spd_length_l -= ADD_VALUE_LR;
		 spd_length = spd_length_l;
	}
	// modify target!=3 to make unreachable statament
	else if(RIGHT_WHEEL == which_wheel )
	{
		if(target == 1)
		{
			spd_length_r += ADD_VALUE_FB;
			if(spd_length_r > TEMP1_F)
				spd_length_r = TEMP1_F;
		}
		else if(target == 2)
		{
			spd_length_r -= ADD_VALUE_FB;
			if(spd_length_r < -TEMP1_B)
				spd_length_r = -TEMP1_B;
		}
		else if(target == 3)
			spd_length_r -= ADD_VALUE_LR;
		else if(target == 4)
			spd_length_r += ADD_VALUE_LR;
		spd_length = spd_length_r;
	}

#define TEMP 1000
	if(spd_length > TEMP)
		spd_length = TEMP;
	if(spd_length < -TEMP)
		spd_length = -TEMP;
//	printf("target:%d\r\n",target);
//	printf("spd=%.1lf\r\n",spd_v);
//	printf("l=%d\n",spd_length);
//	printf("tem=%.1lf\r\n",temp);
//	printf(",%.1lf,%.1lf\n",spd_length_l,spd_length_r);
	return (int)(p->Kp * spd_v + p->Ki * (float)spd_length + p->Kd * temp);
}


int main() {
    printf("%d", PID_Cal_Speed(1, 1, 1));
}

