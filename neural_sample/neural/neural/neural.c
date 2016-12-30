#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#define NUM_LEARN 10000 //�w�K�̌J��Ԃ���
#define NUM_SAMPLE 4 //�g���[�j���O�f�[�^�̃T���v�����i�����ł͘_����H�Ȃ̂�4�A�w�K1��̒��ł̃��[�v�񐔁j
#define NUM_INPUT 2 //���͑w�̐��i�_����H�̓��́j
#define NUM_HIDDEN 3 //���ԑw�̐��i�K���j
#define NUM_OUTPUT 1 //�o�͑w�̐��i�o�͐�1�j
#define EPSILON 0.1 //�w�K���̏d�ݏC���萔�in�j


double sigmoid(double x) { //�V�O���C�h�֐�
	double f;
	f = 1.0 / (1.0 + exp(-x));
	return f;//�߂��l���֐��̒l�ɂȂ�
}

int tx[NUM_SAMPLE][NUM_INPUT], ty[NUM_SAMPLE][NUM_OUTPUT]; //���ꂼ����́A�o�̓g���[�j���O�f�[�^���i�[����
double x[NUM_INPUT + 1], h[NUM_HIDDEN + 1], y[NUM_OUTPUT]; //���́A���ԁA�o�͂��ꂼ��̑f�q�̒l�B�{1�͂������l�\���p
double w1[NUM_INPUT + 1][NUM_HIDDEN], w2[NUM_HIDDEN + 1][NUM_OUTPUT]; //���́[���ԁA���ԁ[�o�͂̂������l
double h_back[NUM_HIDDEN + 1], y_back[NUM_OUTPUT]; //���ԁA�o�͋t�`�ԗ�

int main(void) {
	int ilearn, isample, i, j;
	double net_input, epsilon;
	int inet_input;
	FILE *fp;
	char *fname = "result.txt";
	char *str;
	int c;

	fp = fopen(fname, "w");
	if(fp == NULL) {
		printf("�t�@�C���J���Ȃ���\n");
		return 0;
	}

	srand((unsigned)time(NULL));

	epsilon = (float)EPSILON;

	//���t�f�[�^ tx:���́Aty:�o��
	tx[0][0] = 0;
	tx[0][1] = 0;
	ty[0][0] = 0;

	tx[1][0] = 0;
	tx[1][1] = 1;
	ty[1][0] = 1;

	tx[2][0] = 1;
	tx[2][1] = 0;
	ty[2][0] = 1;

	tx[3][0] = 1;
	tx[3][1] = 1;
	ty[3][0] = 0;

	//�d�݂Ƀ����_���ȏ����l��^����

	//���́[���Ԃ̌����萔�̏�����
	for (i = 0; i < NUM_INPUT + 1; i++) {
		for (j = 0; j < NUM_HIDDEN; j++) {
			w1[i][j] = (double)rand() / RAND_MAX / 1.0;
		}
	}

	//���ԁ[�o�͂̌����萔�̏�����
	for (i = 0; i < NUM_HIDDEN + 1; i++) {
		for (j = 0; j < NUM_OUTPUT; j++) {
			w2[i][j] = (double)rand() / RAND_MAX / 1.0;
		}
	}

	//�w�K�̌J��Ԃ����[�v
	for (ilearn = 0; ilearn < NUM_LEARN; ilearn++) {
		//�P���f�[�^�Ɋւ��郋�[�v
		for (isample = 0; isample < NUM_SAMPLE; isample++) {
			//�������̓���
			//�P���f�[�^�ɏ]���āA�l�b�g���[�N�ւ̓��͐ݒ�
			for (i = 0; i < NUM_INPUT; i++) {
				x[i] = tx[isample][i];
			}

			//臒l�ݒ�x[NUM_INPUT] = 1.0
			x[NUM_INPUT] = (double)1.0;

			//�B��f�q�̌v�Z
			for (j = 0; j < NUM_HIDDEN; j++) {
				net_input = 0;
				for (i = 0; i < NUM_INPUT + 1; i++) {
					net_input = net_input + w1[i][j] * x[i];
				}

				//�V�O���C�h�̓K�p
				h[j] = sigmoid(net_input);
			}

			//臒l�ݒ�h[NUM_HIDDEN] = 1.0
			h[NUM_HIDDEN] = (double)1.0;

			//�o�͑f�q�̌v�Z
			for (j = 0; j < NUM_OUTPUT; j++) {
				net_input = 0;
				for (i = 0; i < NUM_HIDDEN + 1; i++) {
					net_input = net_input + w2[i][j] * h[i];
				}

				//�V�O���C�h�̓K�p
				y[j] = sigmoid(net_input);

				printf("ilearn = %d, ty[isample][j] = %d ", ilearn, ty[isample][j]);
				printf("y > %lf (%d.%d)\n", y[j], tx[isample][0], tx[isample][1]);
				// str = "ilearn = %d, ty[%d][%d] = %d y > %lf (%d.%d)\n";
				fprintf(fp, "ilearn = %d, ty[%d][%d] = %d y > %lf (%d.%d)\n", ilearn, isample, j, ty[isample][j], y[j], tx[isample][0], tx[isample][1]);
			}

			//�t�����̓���
			//�o�͑f�q�̋t�`�d
			for (j = 0; j < NUM_OUTPUT; j++) {
				y_back[j] = (y[j] - ty[isample][j]) * ((double)1.0 - y[j]) * y[j];
			}

			//�B�ꂻ���j�~�̋t�`�d
			for (i = 0; i < NUM_HIDDEN; i++) {
				net_input = 0;
				for (j = 0; j < NUM_OUTPUT; j++) {
					net_input = net_input + w2[i][j] * y_back[j];
				}
				h_back[i] = net_input * ((double)1.0 - h[i]) * h[i];
			}

			//�d�݂̏C��
			for (i = 0; i < NUM_INPUT + 1; i++) {
				for (j = 0; j < NUM_HIDDEN; j++) {
					w1[i][j] = w1[i][j] - epsilon * x[i] * h_back[j];
				}
			}
			for (i = 0; i < NUM_HIDDEN + 1; i++) {
				for (j = 0; j < NUM_OUTPUT; j++) {
					w2[i][j] = w2[i][j] - epsilon * h[i] * y_back[j];
				}
			}

		}	 //�P���f�[�^�̃��[�v�I���

	}	//�w�K�f�[�^�̃��[�v�I���

	fclose(fp);
	printf("�t�@�C���̏������݂��I��������\n");

	return 0;
}