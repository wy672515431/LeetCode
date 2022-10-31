#include<stdio.h>

#define LEFT_WHEEL  1
#define RIGHT_WHEEL 2

#define ADD_VALUE_FB 10
#define ADD_VALUE_LR 11

struct spd {
    float spd_length;
    float spd_length_l;
    float spd_length_r;
};

struct pid_s{
    float Kp;
    float Ki;
    float Kd;
	struct spd *allspd;
};


// float spd_length = 0.0,spd_length_l = 0.0, spd_length_r = 0.0;
float spd_v = 0;	 //the speed after filter
int last_target = 0;
int PID_Cal_Speed(int current,int target,unsigned char which_wheel)
{
	int temp;
	
	struct pid_s TTQ;
	struct pid_s *p;
	struct spd spd;

	
	p = &TTQ;
	p->allspd = &spd;
	p->Kp = 1;
	p->Ki = 1;
	p->Kd = 1;

	//init speed
	p->allspd->spd_length = 0.0;
	p->allspd->spd_length_l = 0.0;
	p->allspd->spd_length_r = 0.0;
	
	
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
			p->allspd->spd_length_l += (int)temp;
		else if(RIGHT_WHEEL == which_wheel)
			p->allspd->spd_length_r += (int)temp;
	}
#define TEMP1_F	5
#define TEMP1_B 2
	if(LEFT_WHEEL == which_wheel)
	{
		if(target == 1)
		{
			p->allspd->spd_length_l += ADD_VALUE_FB;
			if(p->allspd->spd_length_l > TEMP1_F)
				p->allspd->spd_length_l = TEMP1_F;
		}
		else if(target == 2)
		{
			p->allspd->spd_length_l -= ADD_VALUE_FB;
			if(p->allspd->spd_length_l < -TEMP1_B)
				p->allspd->spd_length_l = -TEMP1_B;
		}
		else if(target == 3)
			p->allspd->spd_length_l += ADD_VALUE_LR;
		else if(target == 4)
			p->allspd->spd_length_l -= ADD_VALUE_LR;
		 p->allspd->spd_length = p->allspd->spd_length_l;
	}
	// modify target!=3 to make unreachable statament
	else if(RIGHT_WHEEL == which_wheel )
	{
		if(target == 1)
		{
			p->allspd->spd_length_r += ADD_VALUE_FB;
			if(p->allspd->spd_length_r > TEMP1_F)
				p->allspd->spd_length_r = TEMP1_F;
		}
		else if(target == 2)
		{
			p->allspd->spd_length_r -= ADD_VALUE_FB;
			if(p->allspd->spd_length_r < -TEMP1_B)
				p->allspd->spd_length_r = -TEMP1_B;
		}
		else if(target == 3)
			p->allspd->spd_length_r -= ADD_VALUE_LR;
		else if(target == 4)
			p->allspd->spd_length_r += ADD_VALUE_LR;
		p->allspd->spd_length = p->allspd->spd_length_r;
	}

#define TEMP 1000
	if(p->allspd->spd_length > TEMP)
		p->allspd->spd_length = TEMP;
	if(p->allspd->spd_length < -TEMP)
		p->allspd->spd_length = -TEMP;
//	printf("target:%d\r\n",target);
//	printf("spd=%.1lf\r\n",spd_v);
//	printf("l=%d\n",spd_length);
//	printf("tem=%.1lf\r\n",temp);
//	printf(",%.1lf,%.1lf\n",spd_length_l,spd_length_r);
	return (int)(p->Kp * spd_v + p->Ki * (float)p->allspd->spd_length + p->Kd * temp);
}

int main() {
	printf("%d", PID_Cal_Speed(1, 2, 2));
}

