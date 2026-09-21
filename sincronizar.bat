@echo off
setlocal
cd /d "%~dp0"

echo ========================================
echo   DAM-2: Sincronizar con GitHub
echo ========================================
echo.

echo [1/4] Descargando cambios remotos (pull)...
git pull
if errorlevel 1 (
    echo.
    echo [ERROR] Fallo en git pull. Comprueba conexion o conflictos.
    pause
    exit /b 1
)

echo.
echo [2/4] Anadiendo los cambios (git add)...
git add -A
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
git commit -m "%mensaje%"
if errorlevel 1 (
    echo.
    echo [AVISO] No habia cambios que guardar. Continuando...
)

echo.
echo [4/4] Subiendo a GitHub (push)...
git push
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