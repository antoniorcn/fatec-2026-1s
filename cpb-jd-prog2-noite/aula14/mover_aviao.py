import pygame

pygame.init()

SCREEN_SIZE = (800, 600)

PRETO = (0, 0, 0)
VERMELHO = (255, 0, 0)

screen = pygame.display.set_mode(SCREEN_SIZE, 0, 32)
aviao_img = pygame.image.load("./images/aviao.png")
tiro_img = pygame.image.load("./images/tiro.png")

jogando = True

aviao = {"x": 0, "y": 300,
         "vel_x": 0, "vel_y": 0, "imagem": aviao_img}

tiro = {"x": 0, "y": 0, "vel": 0,
        "imagem": tiro_img, "mostrar": False}

while jogando:
    # Calcular as regras
    aviao["x"] = aviao["x"] + aviao["vel_x"]
    aviao["y"] = aviao["y"] + aviao["vel_y"]
    tiro["y"] = tiro["y"] + tiro["vel"]

    # Desenhar a tela
    screen.fill( PRETO )
    screen.blit( aviao["imagem"], (aviao["x"], aviao["y"]) )

    if tiro["mostrar"]:
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
                tiro["mostrar"] = True
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