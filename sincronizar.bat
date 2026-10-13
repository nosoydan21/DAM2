@echo off
setlocal
cd /d "%~dp0"

echo ========================================
echo   DAM-2: Sincronizar con GitHub
echo ========================================
echo.

REM --- Localizar Git (por si no esta en el PATH) ---
set "GIT=git"
where git >nul 2>nul
if errorlevel 1 (
    if exist "%ProgramFiles%\Git\cmd\git.exe" (
        set "GIT=%ProgramFiles%\Git\cmd\git.exe"
    ) else (
        echo [ERROR] No se encuentra Git. Instala Git for Windows:
        echo         https://git-scm.com/download/win
        pause
        exit /b 1
    )
)

REM --- Configurar identidad de Git la primera vez ---
"%GIT%" config user.email >nul 2>nul
if errorlevel 1 (
    echo [INFO] Configurando la identidad de Git por primera vez...
    "%GIT%" config --global user.name "Dan"
    "%GIT%" config --global user.email "2907709@alu.murciaeduca.es"
)

echo [1/4] Descargando cambios remotos (pull)...
"%GIT%" pull --no-edit
if errorlevel 1 (
    echo.
    echo [ERROR] Fallo en git pull. Comprueba conexion o resuelve conflictos.
    pause
    exit /b 1
)

echo.
echo [2/4] Anadiendo los cambios (git add)...
"%GIT%" add -A
if errorlevel 1 (
    echo [ERROR] Fallo en git add.
    pause
    exit /b 1
)

echo.
set "mensaje="
set /p "mensaje=  Describe tus cambios (Enter = automatico): "
if not defined mensaje set "mensaje=Actualizacion"

echo [3/4] Guardando los cambios (commit)...
"%GIT%" commit -m "%mensaje%"
if errorlevel 1 (
    echo.
    echo [AVISO] No habia cambios que guardar. Continuando...
)

echo.
echo [4/4] Subiendo a GitHub (push)...
"%GIT%" push
if errorlevel 1 (
    echo.
    echo [ERROR] Fallo en git push.
    pause
    exit /b 1
)

echo.
echo ========================================
echo   LISTO. Sincronizacion completada.
echo ========================================
pause
