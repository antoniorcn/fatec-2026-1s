from typing import Any

import pygame

def recortar( spritesheet, g_id, colunas, largura, altura):
    coluna = g_id % colunas
    linha = g_id // colunas
    x_img = coluna * largura
    y_img = linha * altura
    return spritesheet.subsurface( (x_img, y_img), (largura, altura) )

class Heroi( pygame.sprite.Sprite ):
    def __init__(self, x, y, piso, spritesheet, colunas, largura, altura ) -> None:
        super().__init__()
        self.animacao = [27, 28, 29, 30, 31, 32, 33, 34, 35]
        self.frame = 0
        self.g_id = 0
        self.vidas = 5
        self.x = float(x)
        self.y = float(y)
        self.piso = piso
        self.vel_x = 0.0
        self.vel_y = 0.0
        self.rect = pygame.Rect( (x, y), (largura, altura) )
        self.spritesheet = spritesheet
        self.colunas = colunas
        self.largura = largura
        self.altura = altura
        self.image = recortar(spritesheet, self.animacao[self.g_id],
                              colunas, largura, altura)

    def update(self) -> None:
        super().update()
        gravidade = 0.098
        self.x += self.vel_x
        self.y += self.vel_y
        self.rect.topleft = (int(self.x), int(self.y))
        self.vel_y = self.vel_y + (gravidade * 0.5)
        if self.rect.y >= self.piso:
            self.rect.y = self.piso
            self.vel_y = 0
        self.image = recortar(self.spritesheet, self.animacao[ self.g_id ],
                              self.colunas, self.largura, self.altura)
        if self.frame % 50 == 0:
            self.g_id = self.g_id + 1
        if self.g_id >= self.colunas:
            self.g_id = 0
        self.frame += 1


class Boss( pygame.sprite.Sprite ):
    def __init__(self, x, y, imagem ) -> None:
        super().__init__()
        self.x = float(x)
        self.y = float(y)
        self.life = 100
        self.vel_x = 0.0
        self.vel_y = 0.0
        self.rect = pygame.Rect( (x, y), (128, 128) )
        self.image = imagem    
        self.medo = 0

    def update(self) -> None:
        super().update()
        self.x += self.vel_x
        self.y += self.vel_y
        self.rect.topleft = (int(self.x), int(self.y))
        self.medo = self.medo - 1
        if self.medo < 0:
            self.medo = 0


class Tiro( pygame.sprite.Sprite ):
    def __init__(self, x, y, imagem ) -> None:
        super().__init__()
        self.x = float(x)
        self.y = float(y)
        self.vel_x = 0.0
        self.vel_y = 0.0
        self.rect = pygame.Rect( (x, y), (48, 48) )
        self.image = imagem    

    def update(self) -> None:
        super().update()
        self.x += self.vel_x
        self.y += self.vel_y
        self.rect.topleft = (int(self.x), int(self.y))



LARGURA = 800
ALTURA = 600

tela = pygame.display.set_mode( (LARGURA, ALTURA), 0, 32 )

vitoria = pygame.image.load("./images/vitoria.png").convert_alpha()

cenario1 = pygame.image.load("./images/cenario1.png").convert_alpha()
cenario2 = pygame.image.load("./images/cenario2.png").convert_alpha()

tiro_heroi = pygame.image.load("./images/tiro-heroi.png").convert_alpha()


heroi_spritesheet = pygame.image.load("./images/heroi.png")\
    .convert_alpha()
heroi_spritesheet = pygame.transform.scale(heroi_spritesheet, (1152, 512))
    
boss_spritesheet = pygame.image.load("./images/boss.png")\
    .convert_alpha()
boss = recortar( boss_spritesheet, 12, 4, 96, 96)

cenario1_ready = pygame.transform.scale(cenario1, (LARGURA, ALTURA))
cenario2_ready = pygame.transform.scale(cenario2, (LARGURA, ALTURA))
boss_ready = pygame.transform.scale(boss, (128, 128))
tiro_ready = pygame.transform.scale(tiro_heroi, (48, 48))


heroi_sprite = Heroi(50, 4, 400, heroi_spritesheet, 9, 128, 128)
boss_sprite = Boss(650, 400, boss_ready)

personagens = pygame.sprite.Group()
personagens.add( boss_sprite )
personagens.add( heroi_sprite )

tiros = pygame.sprite.Group()

tamanho_total_barra = 170

jogando = True

estado_jogo = 1  # estado_jogo = 0 (Jogando)     estado_jogo = 1 (Vitoria)

while jogando:

    # Calcula as regras
    # print(heroi_sprite.vel_y)
    if estado_jogo == 0:
        direcao_boss = ((heroi_sprite.rect.x - boss_sprite.rect.x) * 0.001)
        colisoes_tiros = pygame.sprite.spritecollide(boss_sprite, tiros, True)
        for colisao in colisoes_tiros:
            boss_sprite.vel_x = 5
            boss_sprite.medo = 100
            boss_sprite.life -= 10
            print( boss_sprite.life )
            if boss_sprite.life <= 0:
                estado_jogo = 1
            # print(boss_sprite.vel_x)
            # personagens.remove( tiro_sprite )
        else:
            if boss_sprite.medo <= 0:
                boss_sprite.vel_x = direcao_boss

        if pygame.Rect.colliderect( heroi_sprite.rect, boss_sprite.rect ):
            heroi_sprite.vidas -= 1
            boss_sprite.medo = 200
            boss_sprite.vel_x = 5

        personagens.update()
        tiros.update()

        tamanho_barra = tamanho_total_barra * boss_sprite.life / 100

    # print (boss_sprite.rect, boss_sprite.rect.scale_by(0.5, 0.5))

    # print(boss_sprite.vel_x)

    # Atualiza a tela
    if estado_jogo == 0:
        tela.blit( cenario1_ready, (0, 0) )
        tela.blit( cenario2_ready, (0, 0) )
        personagens.draw( tela )
        tiros.draw( tela )
        pygame.draw.rect( tela, (255, 0, 0), heroi_sprite.rect, 3)
        pygame.draw.circle( tela, (0, 0, 255), heroi_sprite.rect.midright, 10, 3)

        pygame.draw.rect( tela, (255, 0, 0), ((597, 10), (tamanho_total_barra + 6, 30)), 3)
        pygame.draw.rect( tela, (255, 255, 0), ((600, 13), (tamanho_barra, 24)))

        for vida in range(heroi_sprite.vidas):
            pygame.draw.circle( tela, (0, 255, 0), (100 + 40 * vida, 20), 10)
    elif estado_jogo == 1:
        tela.blit(vitoria, (100, 60))

    pygame.display.update()

    # Capturar os eventos

    if estado_jogo == 0:
        for e in pygame.event.get():
            if e.type == pygame.QUIT:
                jogando = False
            if e.type == pygame.KEYDOWN:
                if e.key == pygame.K_d:
                    heroi_sprite.vel_x = 1
                if e.key == pygame.K_a:
                    heroi_sprite.vel_x = -1
                if e.key == pygame.K_w:
                    heroi_sprite.vel_y = -5
                    print("Pulou")
                if e.key == pygame.K_e:
                    # print(heroi_sprite.rect.midright)

                    # print(tiro_sprite.rect)
                    (tiro_x, tiro_y) = heroi_sprite.rect.midright
                    tiro = Boss(tiro_x, tiro_y, tiro_ready)   
                    tiro.vel_x = 0.3
                    tiros.add( tiro )
            if e.type == pygame.KEYUP:
                if e.key == pygame.K_RIGHT or e.key == pygame.K_LEFT:
                    heroi_sprite.vel_x = 0
    elif estado_jogo == 1:          
        for e in pygame.event.get():
            if e.type == pygame.QUIT:
                jogando = False
            if e.type == pygame.KEYDOWN:
                if e.key == pygame.K_RETURN:
                    estado_jogo = 0