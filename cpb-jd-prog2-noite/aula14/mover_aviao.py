import pygame

pygame.init()

SCREEN_SIZE = (800, 600)

PRETO = (0, 0, 0)
VERMELHO = (255, 0, 0)

screen = pygame.display.set_mode(SCREEN_SIZE, 0, 32)
aviao_img = pygame.image.load("./images/aviao.png")
tiro_img = pygame.image.load("./images/tiro.png")
inimigo_img = pygame.image.load("./images/inimigo.png")


def testa_colisao( x1, x2, y1, y2, x3, x4, y3, y4 ):
    colisao_horizontal = False
    colisao_vertical = False
    if x3 < x2 and x1 <= x4:
        colisao_horizontal = True
    if y3 < y2 and y1 <= y4:
        colisao_vertical = True
    # return colisao_horizontal and colisao_vertical
    if colisao_horizontal and colisao_vertical:
        return True
    else:
        return False

jogando = True

aviao = {"x": 0, "y": 300,
         "vel_x": 0, "vel_y": 0, "imagem": aviao_img}

inimigo = {"x": 0, "y": 10,
         "vel_x": 0, "vel_y": 0, "imagem": inimigo_img,
         "mostrar": True, "hp": 30}

tiro = {"x": 0, "y": 600, "vel": 0,
        "imagem": tiro_img, "mostrar": False, "dano": 10}

while jogando:
    colidiu = False
    # Calcular as regras
    aviao["x"] = aviao["x"] + aviao["vel_x"]
    aviao["y"] = aviao["y"] + aviao["vel_y"]
    tiro["y"] = tiro["y"] + tiro["vel"]

    if inimigo["hp"] > 0: 
#                                  x1          x2              y1         y2
        colidiu = testa_colisao(tiro["x"], tiro["x"] + 49, tiro["y"], tiro["y"] + 54,
#                   x3           x4                 y3              y4
                  inimigo["x"], inimigo["x"] + 64, inimigo["y"], inimigo["y"] + 64)
    if colidiu:
        tiro["mostrar"] = False
        inimigo["hp"] = inimigo["hp"] - tiro["dano"]
        tiro["dano"] = 0

    
        


    # Desenhar a tela
    screen.fill( PRETO )
    screen.blit( aviao["imagem"], (aviao["x"], aviao["y"]) )

    if inimigo["hp"] > 0:
        screen.blit( inimigo["imagem"], (inimigo["x"], inimigo["y"]))

    if tiro["dano"] > 0:
        screen.blit( tiro["imagem"], (tiro["x"], tiro["y"]) )

    pygame.display.update()

    # Capturar os eventos
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            jogando = False
        elif e.type == pygame.KEYDOWN:
            if e.key == pygame.K_RIGHT:
                aviao["vel_x"] = 1
            elif e.key == pygame.K_LEFT:
                aviao["vel_x"] = -1
            elif e.key == pygame.K_UP:
                aviao["vel_y"] = -1
            elif e.key == pygame.K_DOWN:
                aviao["vel_y"] = 1
            elif e.key == pygame.K_f:
                tiro["dano"] = 10
                tiro["x"] = aviao["x"]
                tiro["y"] = aviao["y"]
                tiro["vel"] = -1
        elif e.type == pygame.KEYUP:
            if e.key == pygame.K_RIGHT:
                aviao["vel_x"] = 0
            elif e.key == pygame.K_LEFT:
                aviao["vel_x"] = 0
            elif e.key == pygame.K_UP:
                aviao["vel_y"] = 0
            elif e.key == pygame.K_DOWN:
                aviao["vel_y"] = 0
            

print("Fim do jogo")