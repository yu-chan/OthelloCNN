#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#define NUM_LEARN 10000 //学習の繰り返し回数
#define NUM_SAMPLE 4 //トレーニングデータのサンプル数（ここでは論理回路なので4個、学習1回の中でのループ回数）
#define NUM_INPUT 2 //入力層の数（論理回路の入力）
#define NUM_HIDDEN 3 //中間層の数（適当）
#define NUM_OUTPUT 1 //出力層の数（出力数1個）
#define EPSILON 0.1 //学習時の重み修正定数（n）


double sigmoid(double x) { //シグモイド関数
	double f;
	f = 1.0 / (1.0 + exp(-x));
	return f;//戻す値が関数の値になる
}

int tx[NUM_SAMPLE][NUM_INPUT], ty[NUM_SAMPLE][NUM_OUTPUT]; //それぞれ入力、出力トレーニングデータを格納する
double x[NUM_INPUT + 1], h[NUM_HIDDEN + 1], y[NUM_OUTPUT]; //入力、中間、出力それぞれの素子の値。＋1はしきい値表現用
double w1[NUM_INPUT + 1][NUM_HIDDEN], w2[NUM_HIDDEN + 1][NUM_OUTPUT]; //入力ー中間、中間ー出力のしきい値
double h_back[NUM_HIDDEN + 1], y_back[NUM_OUTPUT]; //中間、出力逆伝番量

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
		printf("ファイル開けないお\n");
		return 0;
	}

	srand((unsigned)time(NULL));

	epsilon = (float)EPSILON;

	//教師データ tx:入力、ty:出力
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

	//重みにランダムな初期値を与える

	//入力ー中間の結合定数の初期化
	for (i = 0; i < NUM_INPUT + 1; i++) {
		for (j = 0; j < NUM_HIDDEN; j++) {
			w1[i][j] = (double)rand() / RAND_MAX / 1.0;
		}
	}

	//中間ー出力の結合定数の初期化
	for (i = 0; i < NUM_HIDDEN + 1; i++) {
		for (j = 0; j < NUM_OUTPUT; j++) {
			w2[i][j] = (double)rand() / RAND_MAX / 1.0;
		}
	}

	//学習の繰り返しループ
	for (ilearn = 0; ilearn < NUM_LEARN; ilearn++) {
		//訓練データに関するループ
		for (isample = 0; isample < NUM_SAMPLE; isample++) {
			//順方向の動作
			//訓練データに従って、ネットワークへの入力設定
			for (i = 0; i < NUM_INPUT; i++) {
				x[i] = tx[isample][i];
			}

			//閾値設定x[NUM_INPUT] = 1.0
			x[NUM_INPUT] = (double)1.0;

			//隠れ素子の計算
			for (j = 0; j < NUM_HIDDEN; j++) {
				net_input = 0;
				for (i = 0; i < NUM_INPUT + 1; i++) {
					net_input = net_input + w1[i][j] * x[i];
				}

				//シグモイドの適用
				h[j] = sigmoid(net_input);
			}

			//閾値設定h[NUM_HIDDEN] = 1.0
			h[NUM_HIDDEN] = (double)1.0;

			//出力素子の計算
			for (j = 0; j < NUM_OUTPUT; j++) {
				net_input = 0;
				for (i = 0; i < NUM_HIDDEN + 1; i++) {
					net_input = net_input + w2[i][j] * h[i];
				}

				//シグモイドの適用
				y[j] = sigmoid(net_input);

				printf("ilearn = %d, ty[isample][j] = %d ", ilearn, ty[isample][j]);
				printf("y > %lf (%d.%d)\n", y[j], tx[isample][0], tx[isample][1]);
				// str = "ilearn = %d, ty[%d][%d] = %d y > %lf (%d.%d)\n";
				fprintf(fp, "ilearn = %d, ty[%d][%d] = %d y > %lf (%d.%d)\n", ilearn, isample, j, ty[isample][j], y[j], tx[isample][0], tx[isample][1]);
			}

			//逆方向の動作
			//出力素子の逆伝播
			for (j = 0; j < NUM_OUTPUT; j++) {
				y_back[j] = (y[j] - ty[isample][j]) * ((double)1.0 - y[j]) * y[j];
			}

			//隠れそう阻止の逆伝播
			for (i = 0; i < NUM_HIDDEN; i++) {
				net_input = 0;
				for (j = 0; j < NUM_OUTPUT; j++) {
					net_input = net_input + w2[i][j] * y_back[j];
				}
				h_back[i] = net_input * ((double)1.0 - h[i]) * h[i];
			}

			//重みの修正
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

		}	 //訓練データのループ終わり

	}	//学習データのループ終わり

	fclose(fp);
	printf("ファイルの書き込みが終了したお\n");

	return 0;
}